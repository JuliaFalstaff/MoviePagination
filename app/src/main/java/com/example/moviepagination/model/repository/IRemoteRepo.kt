package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import io.reactivex.rxjava3.core.Single

interface IRemoteRepo {
    fun getMovieListFromServer(): Single<MovieItemList>
}