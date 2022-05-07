package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.info.MovieInfo
import io.reactivex.rxjava3.core.Single

interface IRemoteRepo {
    fun getMovieNowInTheatre(): Single<MovieItemList>
    fun getMovieByIdFromServer(movieId: String): Single<MovieInfo>
    fun getComingSoonMoviesFromServer(): Single<MovieItemList>
    fun getTOP250Movies(): Single<MovieItemList>
    fun getMostPopularMovies(): Single<MovieItemList>
    fun getMostPopularTVs(): Single<MovieItemList>
}