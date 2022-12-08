package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetComingSoonMovieUseCase
import com.example.moviepagination.domain.usecases.GetMostPopularMoviesUseCase
import com.example.moviepagination.domain.usecases.GetMostPopularTVsUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getComingSoonUseCase: GetComingSoonMovieUseCase,
    private val getPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val getMostPopularTVsUseCase: GetMostPopularTVsUseCase
) : BaseViewModel() {

    private val _comingSoonLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _popularMoviesLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _popularTVsLiveData: MutableLiveData<AppState> = MutableLiveData()
    val comingSoonLiveData: LiveData<AppState> get() = _comingSoonLiveData
    val popularMoviesLiveData: LiveData<AppState> get() = _popularMoviesLiveData
    val popularTVsLiveData: LiveData<AppState> get() = _popularTVsLiveData

    init {
        loadComingSoonMovies()
        loadMostPopularMovies()
        loadMostPopularTVs()
    }

    private fun loadComingSoonMovies() {
        _comingSoonLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val movies = getComingSoonUseCase()
                _comingSoonLiveData.value = AppState.Success(movies)
            } catch (error: Throwable) {
                _comingSoonLiveData.postValue(AppState.Error(error))
            }

        }
    }

    private fun loadMostPopularMovies() {
        _popularMoviesLiveData.postValue(AppState.Loading)
        viewModelScope.launch() {
            try {
                val movies = getPopularMoviesUseCase()
                _popularMoviesLiveData.postValue(AppState.Success(movies))
            } catch (error: Throwable) {
                _popularMoviesLiveData.postValue(AppState.Error(error))
            }
        }
    }

    private fun loadMostPopularTVs() {
        _popularTVsLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val series = getMostPopularTVsUseCase()
                _popularTVsLiveData.value = AppState.Success(series)
            } catch (error: Throwable) {
                _popularTVsLiveData.postValue(AppState.Error(error))
            }
        }
    }
}