package com.example.moviepagination.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.moviepagination.databinding.FragmentSearchBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.presentation.adapters.SearchResultListAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.viewmodel.SearchViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val scope: Scope by lazy { createScope(this) }
    val viewModel: SearchViewModel by inject()
    private var searchQuery = ""
    private var adapter: SearchResultListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRV()
        initViewModel()
        initSearch()
    }

    private fun initViewModel() {
        viewModel.searchMovieLiveData.observe(viewLifecycleOwner) {
            binding.searchProgressBar.visibility = View.INVISIBLE
            renderData(it)
        }
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
                    EMPTY_SEARCH -> {
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
                binding.retryButton.visibility = View.GONE
            }
            is AppState.Error -> {
                binding.searchProgressBar.visibility = View.INVISIBLE
                showError(appState.error)
            }
            is AppState.Loading -> {
                binding.searchProgressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun showErrorConnection() = with(binding) {
        if (!isNetworkAvailable) {
            retryButton.visibility = View.VISIBLE
            retryButton.setOnClickListener {
                initSearch()
            }
        } else {
            retryButton.visibility = View.GONE
        }
    }

    companion object {
        private const val EMPTY_SEARCH = ""
    }
}