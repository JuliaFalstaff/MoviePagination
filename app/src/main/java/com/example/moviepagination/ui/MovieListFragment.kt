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
import com.example.moviepagination.databinding.FragmentMovieListRecyclerViewBinding
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.data.Item
import com.example.moviepagination.ui.adapters.IOnListItemClickListener
import com.example.moviepagination.ui.adapters.MovieListAdapter
import com.example.moviepagination.viewmodel.MovieListViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieListFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentMovieListRecyclerViewBinding? = null
    private val binding get() = _binding!!
    val vm: MovieListViewModel by inject()
    private var adapter: MovieListAdapter? = null
    private val onListItemClickListener: IOnListItemClickListener =
        object : IOnListItemClickListener {
            override fun onItemClick(movie: Item) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
                            putParcelable(MovieInfoFragment.MOVIE_INFO, movie)
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
        _binding = FragmentMovieListRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRecyclerView.adapter = adapter
        vm.getLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderData(it)
        })
        vm.loadMovieListData()
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                binding.movieListRecyclerView.adapter = movieList.let {
                    MovieListAdapter(it, onListItemClickListener)
                }
                adapter?.let {
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