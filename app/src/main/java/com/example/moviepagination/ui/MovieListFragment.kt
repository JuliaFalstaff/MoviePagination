package com.example.moviepagination.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentMainMoviesRvBinding
import com.example.moviepagination.databinding.FragmentMovieListRecyclerViewBinding
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.data.Item
import com.example.moviepagination.ui.adapters.IOnListItemClickListener
import com.example.moviepagination.ui.adapters.MovieListHorizontalAdapter
import com.example.moviepagination.viewmodel.MovieListViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieListFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentMainMoviesRvBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieListViewModel by inject()
    private var nowInTheatreAdapter: MovieListHorizontalAdapter? = null
    private var mostPopularMoviesAdapter: MovieListHorizontalAdapter? = null
    private var mostPopularTVsAdapter: MovieListHorizontalAdapter? = null
    private var comingSoonAdapter: MovieListHorizontalAdapter? = null

    private val onListItemClickListener: IOnListItemClickListener =
        object : IOnListItemClickListener {
            override fun onItemClick(movie: Item) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
                            putString(MovieInfoFragment.MOVIE_INFO, movie.id)
                        }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMoviesRvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesNowInTheatreRecyclerView.adapter = nowInTheatreAdapter
        initViewModels()
    }

    private fun initViewModels() {
        viewModel.getNowInTheatreLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataInTheatre(it)
        })

        viewModel.getComingSoonLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataComingSoon(it)
        })

        viewModel.getPopularMoviesLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataPopularMovies(it)
        })

        viewModel.getPopularTVsLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataPopularTvs(it)
        })

        viewModel.loadComingSoonMovies()
        viewModel.loadMoviesNowInTheatre()
        viewModel.loadMostPopularTVs()
        viewModel.loadMostPopularMovies()
    }

    private fun renderDataInTheatre(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                binding.moviesNowInTheatreRecyclerView.adapter = movieList.let {
                    MovieListHorizontalAdapter(it, onListItemClickListener)
                }
                nowInTheatreAdapter?.let {
                    it.setData(movieList)
                }
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun renderDataPopularMovies(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                binding.mostPopularMovieRecyclerView.adapter = movieList.let {
                    MovieListHorizontalAdapter(it, onListItemClickListener)
                }
                mostPopularMoviesAdapter?.let {
                    it.setData(movieList)
                }
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                        requireContext(),
                        "Error: ${state.error.message}",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun renderDataComingSoon(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                binding.comingSoonRecyclerView.adapter = movieList.let {
                    MovieListHorizontalAdapter(it, onListItemClickListener)
                }
                comingSoonAdapter?.let {
                    it.setData(movieList)
                }

                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                        requireContext(),
                        "Error: ${state.error.message}",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun renderDataPopularTvs(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                binding.mostPopularSeriesRecyclerView.adapter = movieList.let {
                    MovieListHorizontalAdapter(it, onListItemClickListener)
                }
                mostPopularTVsAdapter?.let {
                    it.setData(movieList)
                }
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                        requireContext(),
                        "Error: ${state.error.message}",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}