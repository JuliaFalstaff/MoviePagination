package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.castInfo.ActorInfo
import com.example.moviepagination.model.data.info.MovieInfo
import com.example.moviepagination.model.data.search.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Path

interface IRemoteRepo {
    fun getMovieNowInTheatre(): Single<MovieItemList>
    fun getMovieByIdFromServer(movieId: String): Single<MovieInfo>
    fun getComingSoonMoviesFromServer(): Single<MovieItemList>
    fun getTOP250Movies(): Single<MovieItemList>
    fun getMostPopularMovies(): Single<MovieItemList>
    fun getMostPopularTVs(): Single<MovieItemList>
    fun getActorInfoById(actorId: String): Single<ActorInfo>
    fun getSearchList(expression: String): Single<SearchResult>
}