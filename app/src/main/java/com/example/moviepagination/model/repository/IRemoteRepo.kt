package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.info.MovieInfo
import io.reactivex.rxjava3.core.Single

interface IRemoteRepo {
    fun getMovieListFromServer(): Single<MovieItemList>
    fun getMovieByIdFromServer(movieId: String): Single<MovieInfo>
    fun getComingSoonMoviesFromServer(): Single<MovieItemList>
}