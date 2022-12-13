package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetMovieNowInTheatreUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieListNowInTheatreViewModel(
    private val getNowInTheatreUseCase: GetMovieNowInTheatreUseCase
) : BaseViewModel() {

    private val _nowInTheatre: MutableLiveData<AppState> = MutableLiveData()
    val nowInTheatre: LiveData<AppState> get() = _nowInTheatre

    fun loadMoviesNowInTheatre() {
        _nowInTheatre.postValue(AppState.Loading)
        viewModelScope.launch {
            getNowInTheatreUseCase()
                .catch { error ->
                    _nowInTheatre.postValue(AppState.Error(error))
                }
                .collect { movies ->
                    _nowInTheatre.value = AppState.Success(movies)
                }
        }
    }
}