package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentSavedMoviesRecyclerViewBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.SavedMovieListAdapter
import com.example.moviepagination.presentation.viewmodel.SavedMovieListViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class SavedMovieListFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentSavedMoviesRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SavedMovieListViewModel by inject()
    private var movieListAdapter: SavedMovieListAdapter? = null

    private val onItemClickListener = object : IOnListItemClickListener<MovieInfo> {
        override fun onItemClick(item: MovieInfo) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
                        putString(MovieInfoFragment.MOVIE_INFO, item.id)
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
    ): View {
        _binding = FragmentSavedMoviesRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListAdapter = SavedMovieListAdapter(onItemClickListener)
        binding.savedMovieListRecyclerView.adapter = movieListAdapter
        initViewModels()
    }

    private fun initViewModels() {
        viewModel.savedMoviesLiveData.observe(viewLifecycleOwner) {
            Log.d("MOVIE", it.toString())
            renderData(it)
        }
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.SuccessMovieInfoList -> {
                val movieList = state.dataMovie
                movieListAdapter?.submitList(movieList)
                binding.progressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error SavedList: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG saved list", "${state.error.stackTrace.toString()}")
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = SavedMovieListFragment()
    }
}