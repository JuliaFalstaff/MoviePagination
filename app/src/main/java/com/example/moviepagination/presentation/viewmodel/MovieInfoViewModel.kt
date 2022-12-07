package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.usecases.*
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MovieInfoViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMoviesTrailerUseCase: GetMoviesTrailerUseCase,
    private val saveMovieToMyListUseCase: SaveMovieToMyListUseCase,
    private val deleteMovieFromMyList: DeleteMovieFromMyList,
    private val getSavedMovieByIdUseCase: GetSavedMovieByIdUseCase
) : BaseViewModel() {

    private val _loadMovieLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _loadTrailerLiveData: MutableLiveData<String> = MutableLiveData()
    private val _liveDataIsFav: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsFav: LiveData<Boolean> get() = _liveDataIsFav
    val loadMovieLiveData: LiveData<AppState> get() = _loadMovieLiveData
    val loadTrailerLiveData: MutableLiveData<String> get() = _loadTrailerLiveData

    fun loadMovieById(movieId: String) {
        _loadMovieLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            val movie = getMovieByIdUseCase(movieId)
            _loadMovieLiveData.value = AppState.SuccessMovieInfo(movie)
        }
    }

    fun loadMovieTrailer(movieId: String) {
        viewModelScope.launch {
            val trailer = getMoviesTrailerUseCase(movieId)
            _loadTrailerLiveData.value = trailer.videoId ?: ""
        }
    }

    fun saveMovieToMyList(movie: MovieInfo) {
        viewModelScope.launch {
            saveMovieToMyListUseCase(movie)
        }
    }

    fun checkIsFavourite(movieId: String) {
        viewModelScope.launch {
            val movie = getSavedMovieByIdUseCase(movieId)
            _liveDataIsFav.value = movie.isFavourite
        }
    }

    fun deleteMovieFromMyList(movie: MovieInfo) {
        viewModelScope.launch {
            deleteMovieFromMyList(movie.id)
        }
    }
}