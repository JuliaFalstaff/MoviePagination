package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentTop250Binding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.Top250MoviesAdapter
import com.example.moviepagination.presentation.adapters.Top250TvSeriesAdapter
import com.example.moviepagination.presentation.viewmodel.Top250ViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class Top250Fragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentTop250Binding? = null
    private val binding get() = _binding!!
    private val viewModel: Top250ViewModel by inject()
    private var top250MoviesAdapter: Top250MoviesAdapter? = null
    private var top250TvSeriesAdapter: Top250TvSeriesAdapter? = null
    private val onListItemClickListener: IOnListItemClickListener<Item> =
        object : IOnListItemClickListener<Item> {
            override fun onItemClick(item: Item) {
//                activity?.supportFragmentManager?.apply {
//                    beginTransaction()
//                        .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
//                            putString(MovieInfoFragment.MOVIE_INFO, item.id)
//                        }))
//                        .addToBackStack("")
//                        .commitAllowingStateLoss()
//                }
                findNavController().navigate(
                    Top250FragmentDirections.actionTop250FragmentToMovieInfoFragment(
                        item.id
                    )
                )
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTop250Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        top250MoviesAdapter = Top250MoviesAdapter(onListItemClickListener)
        top250TvSeriesAdapter = Top250TvSeriesAdapter(onListItemClickListener)
        binding.top250MoviesRecyclerView.adapter = top250MoviesAdapter
        binding.top250TvSeriesRecyclerView.adapter = top250TvSeriesAdapter
        initViewModels()
    }

    private fun initViewModels() {
        viewModel.getTop250MoviesLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataTop250Movies(it)
        })
        viewModel.loadTop250Movies()

        viewModel.getTopTVShowMoviesLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataTop250TVs(it)
        })
        viewModel.loadTop250TVs()
    }

    private fun renderDataTop250Movies(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val movies = appState.dataMovie.items
                top250MoviesAdapter?.submitList(movies)
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun renderDataTop250TVs(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val series = appState.dataMovie.items
                top250TvSeriesAdapter?.submitList(series)
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
