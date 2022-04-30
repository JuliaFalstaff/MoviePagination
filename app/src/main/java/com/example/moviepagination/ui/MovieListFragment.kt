package com.example.moviepagination.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviepagination.databinding.FragmentMovieListRecyclerViewBinding
import com.example.moviepagination.viewmodel.MovieListViewModel

class MovieListFragment: Fragment() {

    private var _binding: FragmentMovieListRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieListRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(MovieListViewModel::class.java)
        vm.getLiveData().observe(viewLifecycleOwner, Observer { Log.d("MOVIE", it.toString()) })
        vm.loadMovieListData()

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}