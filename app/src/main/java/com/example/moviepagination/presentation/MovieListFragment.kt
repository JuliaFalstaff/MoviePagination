package com.example.moviepagination.presentation

import android.os.Bundle
import android.view.View
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
            renderDataComingSoon(it)
        }

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            renderDataPopularMovies(it)
        }

        viewModel.popularTVsLiveData.observe(viewLifecycleOwner) {
            renderDataPopularTvs(it)
        }
        viewModel.loadMostPopularMovies()
        viewModel.loadMostPopularTVs()
        viewModel.loadComingSoonMovies()
    }

    private fun renderDataPopularMovies(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                mostPopularMoviesAdapter?.submitList(movieList)
                stopShimmering()
                binding.retryButton.visibility = View.GONE
            }
            is AppState.Loading -> {
                startShimmering()
            }
            is AppState.Error -> {
                showError(state.error)
            }
        }
    }

    private fun renderDataComingSoon(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                comingSoonAdapter?.submitList(movieList)
                stopShimmering()
                binding.retryButton.visibility = View.GONE
            }
            is AppState.Loading -> {
                startShimmering()
            }
            is AppState.Error -> {
                showError(state.error)
            }
        }
    }

    private fun renderDataPopularTvs(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                mostPopularTVsAdapter?.submitList(movieList)
                stopShimmering()
                binding.retryButton.visibility = View.GONE
            }
            is AppState.Loading -> {
                startShimmering()
            }
            is AppState.Error -> {
                showError(state.error)
            }
        }
    }

    override fun showErrorConnection() = with(binding) {
        if (!isNetworkAvailable) {
            retryButton.visibility = View.VISIBLE
            retryButton.setOnClickListener {
                initViewModels()
            }
        } else {
            retryButton.visibility = View.GONE
        }
    }

    private fun startShimmering() = with(binding) {
        comingSoonPlaceholder.shimmerMovieFrameLayout.startShimmer()
        popularMoviesPlaceholder.shimmerMovieFrameLayout.startShimmer()
        popularSeriesPlaceholder.shimmerMovieFrameLayout.startShimmer()
    }

    private fun stopShimmering() = with(binding) {
        comingSoonPlaceholder.shimmerMovieFrameLayout.stopShimmer()
        popularMoviesPlaceholder.shimmerMovieFrameLayout.stopShimmer()
        popularSeriesPlaceholder.shimmerMovieFrameLayout.stopShimmer()
        comingSoonPlaceholder.shimmerMovieFrameLayout.visibility = View.INVISIBLE
        popularMoviesPlaceholder.shimmerMovieFrameLayout.visibility = View.INVISIBLE
        popularSeriesPlaceholder.shimmerMovieFrameLayout.visibility = View.INVISIBLE
        comingSoonRecyclerView.visibility = View.VISIBLE
        mostPopularSeriesRecyclerView.visibility = View.VISIBLE
        mostPopularMovieRecyclerView.visibility = View.VISIBLE
    }

}