package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetAllSavedMoviesUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class SavedMovieListViewModel(
    private val getAllSavedMoviesUseCase: GetAllSavedMoviesUseCase
) : BaseViewModel() {

    private val _savedMovieLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val savedMoviesLiveData: LiveData<AppState> get() = _savedMovieLiveDataToObserve

    init {
        loadSaveMovies()
    }

    private fun loadSaveMovies() {
        _savedMovieLiveDataToObserve.postValue(AppState.Loading)
        viewModelScope.launch {
            val movies = getAllSavedMoviesUseCase()
            _savedMovieLiveDataToObserve.value = AppState.SuccessMovieInfoList(movies)
        }
    }
}