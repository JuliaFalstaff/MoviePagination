package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetSearchListUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase
) : BaseViewModel() {

    private val _searchMovieLiveData: MutableLiveData<AppState> = MutableLiveData()
    val searchMovieLiveData: LiveData<AppState> get() = _searchMovieLiveData

    fun loadSearchResultFromApi(expression: String) {

        viewModelScope.launch {
            getSearchListUseCase(expression)
                .onStart { _searchMovieLiveData.postValue(AppState.Loading) }
                .catch { error ->
                    _searchMovieLiveData.postValue(AppState.Error(error))
                }
                .onEach { search ->
                    _searchMovieLiveData.postValue(AppState.SuccessSearchResult(search))
                }.collect()
        }
    }
}