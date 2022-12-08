package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentMainMoviesRvBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.MovieListAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieListFragment :
    BaseFragment<FragmentMainMoviesRvBinding>(FragmentMainMoviesRvBinding::inflate) {

    override val scope: Scope by lazy { createScope(this) }
    private val viewModel: MovieListViewModel by inject()
    private var mostPopularMoviesAdapter: MovieListAdapter? = null
    private var mostPopularTVsAdapter: MovieListAdapter? = null
    private var comingSoonAdapter: MovieListAdapter? = null
    private val onItemClickListener = object : IOnListItemClickListener<Item> {
        override fun onItemClick(item: Item) {
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToMovieInfoFragment(item.id)
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapters()
        initViewModels()
    }

    private fun setRecyclerViewAdapters() {
        mostPopularMoviesAdapter = MovieListAdapter(onItemClickListener)
        mostPopularTVsAdapter = MovieListAdapter(onItemClickListener)
        comingSoonAdapter = MovieListAdapter(onItemClickListener)
        binding.mostPopularMovieRecyclerView.adapter = mostPopularMoviesAdapter
        binding.mostPopularSeriesRecyclerView.adapter = mostPopularTVsAdapter
        binding.comingSoonRecyclerView.adapter = comingSoonAdapter
    }

    private fun initViewModels() {
        viewModel.comingSoonLiveData.observe(viewLifecycleOwner) {
            Log.d("MOVIE", it.toString())
            renderDataComingSoon(it)
        }

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            Log.d("MOVIE", it.toString())
            renderDataPopularMovies(it)
        }

        viewModel.popularTVsLiveData.observe(viewLifecycleOwner) {
            Log.d("MOVIE", it.toString())
            renderDataPopularTvs(it)
        }
    }

    private fun renderDataPopularMovies(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                mostPopularMoviesAdapter?.submitList(movieList)
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error PopularMovies: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG Popular movies", "${state.error.stackTrace}")
            }
        }
    }

    private fun renderDataComingSoon(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                comingSoonAdapter?.submitList(movieList)
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error Coming: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG Error coming", "${state.error.localizedMessage}")
                Log.d("TAG Error coming", "${state.error.stackTrace.toString()}")
            }
        }
    }

    private fun renderDataPopularTvs(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                mostPopularTVsAdapter?.submitList(movieList)
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error Popular TV: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}