package com.example.moviepagination.domain.repository

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult
import io.reactivex.rxjava3.core.Single

interface IRemoteRepo {
    fun getMovieNowInTheatre(): Single<MovieItemList>
    fun getMovieByIdFromServer(movieId: String): Single<MovieInfo>
    fun getComingSoonMoviesFromServer(): Single<MovieItemList>
    fun getTOP250Movies(): Single<MovieItemList>
    fun getMostPopularMovies(): Single<MovieItemList>
    fun getMostPopularTVs(): Single<MovieItemList>
    fun getActorInfoById(actorId: String): Single<ActorInfo>
    fun getSearchList(expression: String): Single<SearchResult>
    fun getTOP250TVs(): Single<MovieItemList>
    fun getMovieTrailerById(movieId: String): Single<YouTubeTrailer>
}