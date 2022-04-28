package com.example.moviepagination.model

import com.example.moviepagination.model.data.MovieItemList

sealed class AppState {
    data class Success(val dataMovie: MovieItemList): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}