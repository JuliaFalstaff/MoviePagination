package com.example.moviepagination.data.repository

import com.example.moviepagination.data.database.MovieItemListDao
import com.example.moviepagination.data.mapper.MovieItemListMapper
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.repository.ILocalRepo
import io.reactivex.rxjava3.core.Single

class LocalRepoImpl(private val movieItemListDao: MovieItemListDao) : ILocalRepo {

    private val movieItemListMapper = MovieItemListMapper()

    override fun saveMovieList(list: MovieItemList) {
        movieItemListDao.insertAllMovieList(movieItemListMapper.mapListEntityToDbModel(list))
    }

    override fun saveMovie(movie: Item) {
        movieItemListDao.insertMovieToMyList(movieItemListMapper.mapItemEntityToDbModel(movie))
    }

    override fun getAllMovieList(): Single<MovieItemList> {
        return movieItemListDao.getMovieList().map {
            movieItemListMapper.mapDbModelToEntity(it)
        }
    }
}