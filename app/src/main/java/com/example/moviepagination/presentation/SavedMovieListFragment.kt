package com.example.moviepagination.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentSavedMoviesRecyclerViewBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.SavedMovieListAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.viewmodel.SavedMovieListViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class SavedMovieListFragment :
    BaseFragment<FragmentSavedMoviesRecyclerViewBinding>(
        FragmentSavedMoviesRecyclerViewBinding::inflate
    ) {

    override val scope: Scope by lazy { createScope(this) }
    private val viewModel: SavedMovieListViewModel by inject()
    private var movieListAdapter: SavedMovieListAdapter? = null
    private val onItemClickListener = object : IOnListItemClickListener<MovieInfo> {
        override fun onItemClick(item: MovieInfo) {
            findNavController().navigate(
                SavedMovieListFragmentDirections
                    .actionSavedMovieListFragmentToMovieInfoFragment(item.id)
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListAdapter = SavedMovieListAdapter(onItemClickListener)
        binding.savedMovieListRecyclerView.adapter = movieListAdapter
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.savedMoviesLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.SuccessMovieInfoList -> {
                val movieList = state.dataMovie
                movieListAdapter?.submitList(movieList)
                binding.progressBar.visibility = View.INVISIBLE
                if (movieList.isEmpty()) showEmptyDataWarning()
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                showError(state.error)
            }
        }
    }

    private fun showEmptyDataWarning() {
        binding.warningNoDataTextView.visibility = View.VISIBLE
    }
}