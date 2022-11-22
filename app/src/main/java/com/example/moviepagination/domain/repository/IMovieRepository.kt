package com.example.moviepagination.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult

interface IMovieRepository {
    fun getMovieNowInTheatre(): LiveData<MovieItemList>
    fun getMovieByIdFromServer(movieId: String): LiveData<MovieInfo>
    fun getComingSoonMoviesFromServer(): LiveData<MovieItemList>
    fun getTOP250Movies(): LiveData<MovieItemList>
    fun getMostPopularMovies(): LiveData<MovieItemList>
    fun getMostPopularTVs(): LiveData<MovieItemList>
    fun getActorInfoById(actorId: String): LiveData<ActorInfo>
    fun getSearchList(expression: String): LiveData<SearchResult>
    fun getTOP250TVs(): LiveData<MovieItemList>
    fun getMovieTrailerById(movieId: String): LiveData<YouTubeTrailer>
    fun saveMovieList(list: MovieItemList)
    fun getAllMovieList(): LiveData<MovieItemList>
}