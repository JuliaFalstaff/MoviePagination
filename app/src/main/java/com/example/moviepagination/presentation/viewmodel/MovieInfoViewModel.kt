package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.usecases.*
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieInfoViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMoviesTrailerUseCase: GetMoviesTrailerUseCase,
    private val saveMovieToMyListUseCase: SaveMovieToMyListUseCase,
    private val deleteMovieFromMyList: DeleteMovieFromMyList,
    private val getSavedMovieByIdUseCase: GetSavedMovieByIdUseCase
) : BaseViewModel() {

    private val _loadMovieLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _loadTrailerLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _liveDataIsFav: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsFav: LiveData<Boolean> get() = _liveDataIsFav
    val loadMovieLiveData: LiveData<AppState> get() = _loadMovieLiveData
    val loadTrailerLiveData: MutableLiveData<AppState> get() = _loadTrailerLiveData

    private fun loadMovieByIdFromServer(movieId: String) {

        viewModelCustomScope.launch {
            getMovieByIdUseCase(movieId)
                .onStart {
                    _loadMovieLiveData.postValue(AppState.Loading)
                }
                .catch { error ->
                    _loadMovieLiveData.postValue(AppState.Error(error))
                }
                .onEach { movie ->
                    _loadMovieLiveData.value = AppState.SuccessMovieInfo(movie)
                }.collect()
        }
    }

    fun loadMovieTrailer(movieId: String) {
        viewModelCustomScope.launch {
            getMoviesTrailerUseCase(movieId)
                .catch { error ->
                    _loadTrailerLiveData.postValue(AppState.Error(error))
                }
                .onEach { trailer ->
                    _loadTrailerLiveData.value = AppState.SuccessTrailer(trailer.videoId)
                }.collect()
        }
    }

    fun saveMovieToMyList(movie: MovieInfo) {
        viewModelCustomScope.launch {
            saveMovieToMyListUseCase(movie)
        }
    }

    fun checkIsFavouriteAndLoad(movieId: String) {
        _loadMovieLiveData.postValue(AppState.Loading)
        viewModelCustomScope.launch {
            val movie = getSavedMovieByIdUseCase(movieId)
            _liveDataIsFav.value = movie.isFavourite
            if (movie.isFavourite) {
                _loadMovieLiveData.value = AppState.SuccessMovieInfo(movie)
            } else {
                loadMovieByIdFromServer(movieId)
            }
        }
    }

    fun deleteMovieFromMyList(movie: MovieInfo) {
        viewModelCustomScope.launch {
            deleteMovieFromMyList(movie.id)
        }
    }
}