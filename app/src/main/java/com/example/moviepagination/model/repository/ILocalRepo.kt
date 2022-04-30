package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ILocalRepo {
    fun saveMovieList(list: MovieItemList)
    fun getAllMovieList(): Single<MovieItemList>
}