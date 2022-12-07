package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
            Log.d("MOVIE", it.toString())
            renderDataTop250Movies(it)
        }

        viewModel.top250TVShowLiveData.observe(viewLifecycleOwner) {
            Log.d("MOVIE", it.toString())
            renderDataTop250TVs(it)
        }
    }

    private fun renderDataTop250Movies(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movies = appState.dataMovie.items
                top250MoviesAdapter?.submitList(movies)
                binding.top250ProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.top250ProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.top250ProgressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun renderDataTop250TVs(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val series = appState.dataMovie.items
                top250TvSeriesAdapter?.submitList(series)
                binding.top250ProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.top250ProgressBar.visibility = View.VISIBLE
            }
            is AppState.Loading -> {
                binding.top250ProgressBar.visibility = View.VISIBLE
            }
        }
    }
}
