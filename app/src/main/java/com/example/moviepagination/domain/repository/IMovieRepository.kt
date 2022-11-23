package com.example.moviepagination.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult

//interface IMovieRepository: IRemoteRepo, ILocalRepo {
//    override fun getMovieNowInTheatre(): LiveData<MovieItemList>
//    override fun getMovieByIdFromServer(movieId: String): LiveData<MovieInfo>
//    override fun getComingSoonMoviesFromServer(): LiveData<MovieItemList>
//    override fun getTOP250Movies(): LiveData<MovieItemList>
//    override fun getMostPopularMovies(): LiveData<MovieItemList>
//    override fun getMostPopularTVs(): LiveData<MovieItemList>
//    override fun getActorInfoById(actorId: String): LiveData<ActorInfo>
//    override fun getSearchList(expression: String): LiveData<SearchResult>
//    override fun getTOP250TVs(): LiveData<MovieItemList>
//    override fun getMovieTrailerById(movieId: String): LiveData<YouTubeTrailer>
//    override fun saveMovieList(list: MovieItemList)
//    override fun getAllMovieList(): LiveData<MovieItemList>
//}