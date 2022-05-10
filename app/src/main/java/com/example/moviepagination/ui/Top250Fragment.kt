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
import com.example.moviepagination.databinding.FragmentTop250Binding
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.data.Item
import com.example.moviepagination.ui.adapters.IOnListItemClickListener
import com.example.moviepagination.ui.adapters.Top250MoviesAdapter
import com.example.moviepagination.ui.adapters.Top250TvSeriesAdapter
import com.example.moviepagination.viewmodel.Top250ViewModel
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
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTop250Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                val data = appState.dataMovie.items
                binding.top250MoviesRecyclerView.adapter = data.let {
                    Top250MoviesAdapter(it, onListItemClickListener)
                }
                data.let { top250MoviesAdapter?.setTopMovieData(it) }
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
                val data = appState.dataMovie.items
                binding.top250TvSeriesRecyclerView.adapter = data.let {
                    Top250MoviesAdapter(it, onListItemClickListener)
                }
                data.let { top250TvSeriesAdapter?.setTopTvSeriesData(it) }
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

    companion object {
        fun newInstance() = Top250Fragment()
    }
}
