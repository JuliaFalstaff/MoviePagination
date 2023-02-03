package com.example.moviepagination.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentTop250Binding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.Top250MoviesAdapter
import com.example.moviepagination.presentation.adapters.Top250TvSeriesAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.viewmodel.Top250ViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class Top250Fragment : BaseFragment<FragmentTop250Binding>(FragmentTop250Binding::inflate) {

    override val scope: Scope by lazy { createScope(this) }
    private val viewModel: Top250ViewModel by inject()
    private var top250MoviesAdapter: Top250MoviesAdapter? = null
    private var top250TvSeriesAdapter: Top250TvSeriesAdapter? = null
    private val onListItemClickListener: IOnListItemClickListener<Item> =
        object : IOnListItemClickListener<Item> {
            override fun onItemClick(item: Item) {
                findNavController().navigate(
                    Top250FragmentDirections.actionTop250FragmentToMovieInfoFragment(
                        item.id
                    )
                )
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRV()
        showErrorConnection()
        initViewModels()
    }

    private fun setRV() {
        top250MoviesAdapter = Top250MoviesAdapter(onListItemClickListener)
        top250TvSeriesAdapter = Top250TvSeriesAdapter(onListItemClickListener)
        binding.top250MoviesRecyclerView.adapter = top250MoviesAdapter
        binding.top250TvSeriesRecyclerView.adapter = top250TvSeriesAdapter
    }

    private fun initViewModels() {
        viewModel.top250MoviesLiveData.observe(viewLifecycleOwner) {
            renderDataTop250Movies(it)
        }

        viewModel.top250TVShowLiveData.observe(viewLifecycleOwner) {
            renderDataTop250TVs(it)
        }
        viewModel.loadTop250TVs()
        viewModel.loadTop250Movies()
    }

    private fun renderDataTop250Movies(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movies = appState.dataMovie.items
                top250MoviesAdapter?.submitList(movies)
                stopShimmering()
                binding.retryButton.visibility = View.INVISIBLE
            }
            is AppState.Error -> {
                showError(appState.error)
                stopShimmering()
            }
            is AppState.Loading -> {
                startShimmering()
            }
        }
    }

    private fun renderDataTop250TVs(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val series = appState.dataMovie.items
                top250TvSeriesAdapter?.submitList(series)
                stopShimmering()
            }
            is AppState.Error -> {
                showError(appState.error)
                stopShimmering()
            }
            is AppState.Loading -> {
                startShimmering()
            }
        }
    }

    override fun showErrorConnection() = with(binding) {
        if (!isNetworkAvailable) {
            retryButton.visibility = View.VISIBLE
            retryButton.setOnClickListener {
                viewModel.loadTop250TVs()
                viewModel.loadTop250Movies()
            }
        } else {
            retryButton.visibility = View.GONE
        }
    }

    private fun startShimmering() = with(binding) {
        top250MoviesPlaceholder.shimmerMovieFrameLayout.startShimmer()
        top250SeriesPlaceholder.shimmerMovieFrameLayout.startShimmer()
    }

    private fun stopShimmering() = with(binding) {
        top250MoviesPlaceholder.shimmerMovieFrameLayout.stopShimmer()
        top250SeriesPlaceholder.shimmerMovieFrameLayout.stopShimmer()
        top250MoviesPlaceholder.shimmerMovieFrameLayout.visibility = View.INVISIBLE
        top250SeriesPlaceholder.shimmerMovieFrameLayout.visibility = View.INVISIBLE
        top250MoviesRecyclerView.visibility = View.VISIBLE
        top250TvSeriesRecyclerView.visibility = View.VISIBLE
    }
}
