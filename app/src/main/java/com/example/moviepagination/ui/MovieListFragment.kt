package com.example.moviepagination.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentMovieListRecyclerViewBinding
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.data.Item
import com.example.moviepagination.ui.adapters.IOnListItemClickListener
import com.example.moviepagination.ui.adapters.MovieListAdapter
import com.example.moviepagination.viewmodel.MovieListViewModel

class MovieListFragment: Fragment() {

    private var _binding: FragmentMovieListRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: MovieListViewModel
    private var adapter: MovieListAdapter? = null
    private val onListItemClickListener: IOnListItemClickListener = object : IOnListItemClickListener {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieListRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRecyclerView.adapter = adapter

        vm = ViewModelProvider(this).get(MovieListViewModel::class.java)
        vm.getLiveData().observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderData(it)
        })
        vm.loadMovieListData()

    }

    private fun renderData(state: AppState) {
        when(state) {
            is AppState.Success -> {
                val movieList = state.dataMovie.items
                binding.movieListRecyclerView.adapter = movieList.let {
                    MovieListAdapter(it, onListItemClickListener)
                }
                adapter?.let {
                    it.setData(movieList)
                }
            }
            is AppState.Loading -> {}
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
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