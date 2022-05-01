package com.example.moviepagination.model.repository

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.room.MovieDataBase
import com.example.moviepagination.model.room.dao.MovieItemListDao
import com.example.moviepagination.model.room.entities.MovieItemListEntity
import com.example.moviepagination.utils.convertFromDataToEntity
import com.example.moviepagination.utils.convertFromEntityToMovieList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class LocalRepoImpl(private val movieItemListDao: MovieItemListDao) : ILocalRepo {

    override fun saveMovieList(list: MovieItemList) {
            movieItemListDao.insertAllMovieList(MovieItemListEntity(0, list.items))
    }

    override fun getAllMovieList(): Single<MovieItemList> {
        return movieItemListDao.getMovieList().map {
            convertFromEntityToMovieList(it)
        }
    }
}