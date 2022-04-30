package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.room.dao.MovieItemListDao
import com.example.moviepagination.utils.convertFromDataToEntity
import com.example.moviepagination.utils.convertFromEntityToMovieList

class LocalRepoImpl(private val movieListDao: MovieItemListDao) : ILocalRepo {

    override fun saveMovieList(list: MovieItemList) {
        val dataList = convertFromDataToEntity(list)
        movieListDao.insertAllMovieList(dataList)
    }

    override fun getAllMovieList(): MovieItemList {
        return convertFromEntityToMovieList(movieListDao.getMovieList())
    }
}