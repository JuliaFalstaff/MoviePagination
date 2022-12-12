package com.example.moviepagination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetSearchListUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase
) : BaseViewModel() {

    private val _searchMovieLiveData: MutableLiveData<AppState> = MutableLiveData()
    val searchMovieLiveData: LiveData<AppState> get() = _searchMovieLiveData

    fun loadSearchResultFromApi(expression: String) {
        _searchMovieLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val search = getSearchListUseCase(expression)
                _searchMovieLiveData.postValue(AppState.SuccessSearchResult(search))
            } catch (error: Throwable) {
                _searchMovieLiveData.postValue(AppState.Error(error))
                Log.d("Error Search", error.stackTraceToString())
            }
        }
    }
}