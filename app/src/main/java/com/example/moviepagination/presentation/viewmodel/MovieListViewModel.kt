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
        _comingSoonLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            getComingSoonUseCase()
                .catch { error ->
                    _comingSoonLiveData.postValue(AppState.Error(error))
                }
                .collect { movies ->
                    _comingSoonLiveData.value = AppState.Success(movies)
                }
        }
    }

    fun loadMostPopularMovies() {
        _popularMoviesLiveData.postValue(AppState.Loading)
        viewModelScope.launch() {
            getPopularMoviesUseCase()
                .catch { error ->
                    _popularMoviesLiveData.postValue(AppState.Error(error))
                }
                .collect { movies ->
                    _popularMoviesLiveData.postValue(AppState.Success(movies))
                }
        }
    }

    fun loadMostPopularTVs() {
        _popularTVsLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            getMostPopularTVsUseCase()
                .catch { error ->
                    _popularTVsLiveData.postValue(AppState.Error(error))
                }
                .collect { series ->
                    _popularTVsLiveData.value = AppState.Success(series)
                }
        }
    }
}