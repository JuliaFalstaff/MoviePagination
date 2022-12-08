package com.example.moviepagination.domain.repository

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.info.MovieInfo

interface ILocalRepo {
    suspend fun saveMovieList(list: MovieItemList)
    suspend fun saveMovie(movie: MovieInfo)

    //    suspend fun getAllSavedMovieList(): LiveData<MovieItemList>
//    suspend fun getSavedMovieInfo(movieId: String): LiveData<MovieInfo>
    suspend fun getAllSavedMovieList(): List<MovieInfo>
    suspend fun getSavedMovieInfo(movieId: String?): MovieInfo
    suspend fun deleteMovieFromMyList(id: String?)
}