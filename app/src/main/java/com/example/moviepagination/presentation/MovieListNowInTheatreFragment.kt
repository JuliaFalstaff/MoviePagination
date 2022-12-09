package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentNowInTheatreMoviesRecyclerViewBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.NowInTheatreMovieListAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.viewmodel.MovieListNowInTheatreViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieListNowInTheatreFragment : BaseFragment<FragmentNowInTheatreMoviesRecyclerViewBinding>(
    FragmentNowInTheatreMoviesRecyclerViewBinding::inflate
) {

    override val scope: Scope by lazy { createScope(this) }
    private val viewModel: MovieListNowInTheatreViewModel by inject()
    private var nowInTheatreAdapter: NowInTheatreMovieListAdapter? = null
    private val onItemClickListener = object : IOnListItemClickListener<Item> {
        override fun onItemClick(item: Item) {
            findNavController().navigate(
                MovieListNowInTheatreFragmentDirections
                    .actionMovieListNowInTheatreFragmentToMovieInfoFragment(
                        item.id
                    )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()
        initViewModels()
    }

    private fun setRecyclerViewAdapter() {
        nowInTheatreAdapter = NowInTheatreMovieListAdapter(onItemClickListener)
        binding.nowInTheatreMovieListRecyclerView.adapter = nowInTheatreAdapter
    }

    private fun initViewModels() {
        viewModel.nowInTheatre.observe(viewLifecycleOwner) {
            Log.d("MOVIE", it.toString())
            renderDataInTheatre(it)
        }
    }

    private fun renderDataInTheatre(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                nowInTheatreAdapter?.submitList(movieList)
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                showError(state.error)
            }
        }
    }
}