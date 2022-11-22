package com.example.moviepagination.data.repository

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.data.database.MovieItemListDao
import com.example.moviepagination.data.model.MovieItemListDbModel
import com.example.moviepagination.domain.repository.ILocalRepo
import com.example.moviepagination.utils.convertFromEntityToMovieList
import io.reactivex.rxjava3.core.Single

class LocalRepoImpl(private val movieItemListDao: MovieItemListDao) : ILocalRepo {

    override fun saveMovieList(list: MovieItemList) {
            movieItemListDao.insertAllMovieList(MovieItemListDbModel(0, list.items))
    }

    override fun getAllMovieList(): Single<MovieItemList> {
        return movieItemListDao.getMovieList().map {
            convertFromEntityToMovieList(it)
        }
    }
}