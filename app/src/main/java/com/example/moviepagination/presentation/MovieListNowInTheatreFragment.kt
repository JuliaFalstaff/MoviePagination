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
import com.example.moviepagination.databinding.FragmentNowInTheatreMoviesRecyclerViewBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.adapters.IOnListItemClickListener
import com.example.moviepagination.presentation.adapters.MovieListAdapter
import com.example.moviepagination.presentation.adapters.NowInTheatreMovieListAdapter
import com.example.moviepagination.presentation.viewmodel.MovieListNowInTheatreViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieListNowInTheatreFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentNowInTheatreMoviesRecyclerViewBinding? = null
    private val binding get() = _binding!!
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNowInTheatreMoviesRecyclerViewBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapters()
        initViewModels()
    }

    private fun setRecyclerViewAdapters() {
        nowInTheatreAdapter = NowInTheatreMovieListAdapter(onItemClickListener)
        binding.nowInTheatreMovieListRecyclerView.adapter = nowInTheatreAdapter
    }

    private fun initViewModels() {
        viewModel.nowInTheatre.observe(viewLifecycleOwner, Observer {
            Log.d("MOVIE", it.toString())
            renderDataInTheatre(it)
        })
        viewModel.loadMoviesNowInTheatre()
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
                Toast.makeText(
                    requireContext(),
                    "Error InTheatre: ${state.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}