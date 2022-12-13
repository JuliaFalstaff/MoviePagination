package com.example.moviepagination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val _loadTrailerLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _liveDataIsFav: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsFav: LiveData<Boolean> get() = _liveDataIsFav
    val loadMovieLiveData: LiveData<AppState> get() = _loadMovieLiveData
    val loadTrailerLiveData: MutableLiveData<AppState> get() = _loadTrailerLiveData

    private fun loadMovieByIdFromServer(movieId: String) {
        _loadMovieLiveData.postValue(AppState.Loading)
        viewModelCustomScope.launch {
            try {
                val movie = getMovieByIdUseCase(movieId)
                _loadMovieLiveData.value = AppState.SuccessMovieInfo(movie)
                Log.d("TAG load", "from server")
            } catch (error: Throwable) {
                _loadMovieLiveData.postValue(AppState.Error(error))
            }
        }
    }

    fun loadMovieTrailer(movieId: String) {
        viewModelCustomScope.launch {
            try {
                val trailer = getMoviesTrailerUseCase(movieId)
                _loadTrailerLiveData.value = AppState.SuccessTrailer(trailer.videoId)
            } catch (error: Throwable) {
                _loadTrailerLiveData.postValue(AppState.Error(error))
            }
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
                Log.d("TAG load", "from db Room")
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