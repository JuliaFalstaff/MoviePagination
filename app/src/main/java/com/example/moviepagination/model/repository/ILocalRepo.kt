package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList

interface ILocalRepo {
    fun saveMovieList(list: MovieItemList)
    fun getAllMovieList(): MovieItemList
}