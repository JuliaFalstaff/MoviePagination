package com.example.moviepagination.domain.repository

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult
import io.reactivex.rxjava3.core.Single

interface IRemoteRepo {
    suspend fun getMovieNowInTheatre(): MovieItemList
    suspend fun getMovieByIdFromServer(movieId: String): MovieInfo
    suspend fun getComingSoonMoviesFromServer(): MovieItemList
    suspend fun getTOP250Movies(): MovieItemList
    suspend fun getMostPopularMovies(): MovieItemList
    suspend fun getMostPopularTVs(): MovieItemList
    suspend fun getActorInfoById(actorId: String): ActorInfo
    suspend fun getSearchList(expression: String): SearchResult
    suspend fun getTOP250TVs(): MovieItemList
    suspend fun getMovieTrailerById(movieId: String): YouTubeTrailer
}