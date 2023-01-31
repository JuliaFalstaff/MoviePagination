package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetComingSoonMovieUseCase
import com.example.moviepagination.domain.usecases.GetMostPopularMoviesUseCase
import com.example.moviepagination.domain.usecases.GetMostPopularTVsUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
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

    fun loadComingSoonMovies() {
        viewModelScope.launch {
            getComingSoonUseCase()
                .onStart { _comingSoonLiveData.postValue(AppState.Loading) }
                .catch { error ->
                    _comingSoonLiveData.postValue(AppState.Error(error))
                }
                .onEach { movies ->
                    _comingSoonLiveData.value = AppState.Success(movies)
                }.collect()
        }
    }

    fun loadMostPopularMovies() {
        viewModelScope.launch() {
            getPopularMoviesUseCase()
                .onStart { _popularMoviesLiveData.postValue(AppState.Loading) }
                .catch { error ->
                    _popularMoviesLiveData.postValue(AppState.Error(error))
                }
                .onEach { movies ->
                    _popularMoviesLiveData.postValue(AppState.Success(movies))
                }.collect()
        }
    }

    fun loadMostPopularTVs() {
        viewModelScope.launch {
            getMostPopularTVsUseCase()
                .onStart { _popularTVsLiveData.postValue(AppState.Loading) }
                .catch { error ->
                    _popularTVsLiveData.postValue(AppState.Error(error))
                }
                .onEach { series ->
                    _popularTVsLiveData.value = AppState.Success(series)
                }.collect()
        }
    }
}