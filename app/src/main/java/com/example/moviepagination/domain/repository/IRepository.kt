package com.example.moviepagination.domain.repository

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getMovieNowInTheatre(): Flow<MovieItemList>
    suspend fun getMovieByIdFromServer(movieId: String): Flow<MovieInfo>
    suspend fun getComingSoonMoviesFromServer(): Flow<MovieItemList>
    suspend fun getTOP250Movies(): Flow<MovieItemList>
    suspend fun getMostPopularMovies(): Flow<MovieItemList>
    suspend fun getMostPopularTVs(): Flow<MovieItemList>
    suspend fun getActorInfoById(actorId: String): Flow<ActorInfo>
    suspend fun getSearchList(expression: String): Flow<SearchResult>
    suspend fun getTOP250TVs(): Flow<MovieItemList>
    suspend fun getMovieTrailerById(movieId: String): Flow<YouTubeTrailer>
    suspend fun saveMovie(movie: MovieInfo)
    suspend fun getAllSavedMovieList(): List<MovieInfo>
    suspend fun getSavedMovieInfo(movieId: String?): MovieInfo
    suspend fun deleteMovieFromMyList(id: String?)
}