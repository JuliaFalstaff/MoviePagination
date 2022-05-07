package com.example.moviepagination.model

import com.example.moviepagination.model.data.Item
import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.info.MovieInfo

sealed class AppState {
    data class Success(val dataMovie: MovieItemList): AppState()
    data class SuccessMoviesList(val popularMovie: MovieItemList, val inTheatreMovie: MovieItemList,
                                 val comingSoonMovie: MovieItemList, val popularTVs: MovieItemList): AppState()
    data class SuccessMovieInfo(val dataMovie: MovieInfo): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}