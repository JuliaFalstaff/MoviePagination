package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentSearchBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.presentation.adapters.SearchResultListAdapter
import com.example.moviepagination.presentation.viewmodel.SearchViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class SearchFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    val viewModel: SearchViewModel by inject()
    private var searchQuery = ""
    private var adapter: SearchResultListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRV()
        viewModel.searchMovieLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
        initSearch()
    }

    private fun setRV() {
        adapter = SearchResultListAdapter()
        binding.searchResultRecyclerView.adapter = adapter
        adapter?.listener = { item ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieInfoFragment(
                    item.id
                )
            )
        }
    }

    private fun initSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return when (searchQuery.trim()) {
                    "" -> {
                        false
                    }
                    else -> {
                        viewModel.loadSearchResultFromApi(query.toString())
                        true
                    }
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery = newText.toString()
                viewModel.loadSearchResultFromApi(searchQuery)
                return true
            }
        })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessSearchResult -> {
                val data = appState.searchResult.results
                adapter?.submitList(data)
                binding.searchProgressBar.visibility = View.INVISIBLE
                Log.d("TAG SUCCESS", "${appState.searchResult}")
            }
            is AppState.Error -> {
                binding.searchProgressBar.visibility = View.INVISIBLE
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is AppState.Loading -> {
                binding.searchProgressBar.visibility = View.VISIBLE
            }
        }
    }
}