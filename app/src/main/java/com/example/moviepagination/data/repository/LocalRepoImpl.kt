package com.example.moviepagination.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moviepagination.data.database.MovieItemListDao
import com.example.moviepagination.data.mapper.MovieInfoMapper
import com.example.moviepagination.data.mapper.MovieItemListMapper
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.repository.ILocalRepo
import io.reactivex.rxjava3.core.Single

class LocalRepoImpl(private val movieItemListDao: MovieItemListDao) : ILocalRepo {

    private val movieItemListMapper = MovieItemListMapper()
    private val movieInfoMapper = MovieInfoMapper()

    override fun saveMovieList(list: MovieItemList) {
        movieItemListDao.insertAllMovieList(movieItemListMapper.mapListEntityToDbModel(list))
    }

    override fun saveMovie(movie: Item) {
        movieItemListDao.insertMovieToMyList(movieItemListMapper.mapItemEntityToDbModel(movie))
    }

    override fun getAllSavedMovieList(): LiveData<MovieItemList> {
        return Transformations.map(movieItemListDao.getMovieList()){
            movieItemListMapper.mapDbModelToEntity(it)
        }
    }

    override fun getSavedMovieInfo(movieId: String): LiveData<MovieInfo> {
        return Transformations.map(movieItemListDao.getSavedMovieInfo(movieId)) {
            movieInfoMapper.mapMovieInfoDbModelToEntity(it)
        }
    }
}