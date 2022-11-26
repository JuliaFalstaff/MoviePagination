package com.example.moviepagination.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.info.MovieInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ILocalRepo {
    fun saveMovieList(list: MovieItemList): Completable
    fun saveMovie(movie: MovieInfo): Completable
//    fun getAllSavedMovieList(): LiveData<MovieItemList>
//    fun getSavedMovieInfo(movieId: String): LiveData<MovieInfo>

    fun getAllSavedMovieList(): Single<List<MovieInfo>>
    fun getSavedMovieInfo(movieId: String): Single<MovieInfo>
}