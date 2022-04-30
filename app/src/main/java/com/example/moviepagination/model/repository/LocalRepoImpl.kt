package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.room.dao.MovieItemListDao
import com.example.moviepagination.model.room.entities.MovieItemListEntity

class LocalRepoImpl(private val movieListDao: MovieItemListDao) : ILocalRepo {

    override fun saveMovieList(list: MovieItemList) {
        movieListDao.insertAllMovieList(MovieItemListEntity(0, list.items))
    }

    override fun getAllMovieList(): MovieItemList {
        val movieList = movieListDao.getMovieList()
        return MovieItemList(movieList.items)
    }
}