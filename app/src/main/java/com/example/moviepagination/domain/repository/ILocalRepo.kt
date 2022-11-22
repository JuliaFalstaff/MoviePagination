package com.example.moviepagination.domain.repository

import com.example.moviepagination.domain.entities.MovieItemList
import io.reactivex.rxjava3.core.Single

interface ILocalRepo {
    fun saveMovieList(list: MovieItemList)
    fun getAllMovieList(): Single<MovieItemList>
}