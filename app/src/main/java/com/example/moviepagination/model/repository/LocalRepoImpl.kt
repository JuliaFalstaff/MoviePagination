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

class LocalRepoImpl(private val db: MovieDataBase) : ILocalRepo {

    override fun saveMovieList(list: MovieItemList): Completable {
        return Completable.fromCallable {
            db.movieItemListDao.insertAllMovieList(MovieItemListEntity(0, list.items))
        }


//        return movieListDao.insertAllMovieList(MovieItemListEntity(0, list.items))

    }

    override fun getAllMovieList(): Single<MovieItemList> {
        return db.movieItemListDao.getMovieList().map {
            convertFromEntityToMovieList(it)
        }


//        val movieList = movieListDao.getMovieList()
//        return MovieItemList(movieList.items)
    }
}