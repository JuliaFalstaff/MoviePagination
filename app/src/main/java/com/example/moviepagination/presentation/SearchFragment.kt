package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentSearchBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.search.Result
import com.example.moviepagination.presentation.adapters.IOnSearchItemClickListener
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
    private val onListItemClickListener: IOnSearchItemClickListener = object : IOnSearchItemClickListener {
        override fun onItemSearchClick(movie: Result) {
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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchResultRecyclerView.adapter = adapter
        viewModel.getSearchResultLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        initSearch()
    }

    private fun initSearch() {
      binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
          override fun onQueryTextSubmit(query: String?): Boolean {
              return when (searchQuery.trim()) {
                  "" -> {
                      false
                  }
                  else -> {
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
        when(appState) {
            is AppState.SuccessSearchResult -> {
                val data = appState.searchResult.results
                binding.searchResultRecyclerView.adapter = data?.let {
                    SearchResultListAdapter(it, onListItemClickListener)
                }
                data?.let { adapter?.setResultsData(it) }

                Log.d("TAG SUCCESS", "${appState.searchResult}")
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
        fun newInstance() = SearchFragment()
    }
}