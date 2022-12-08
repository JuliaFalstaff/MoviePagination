package com.example.moviepagination.data.repository

import com.example.moviepagination.data.database.MovieItemListDao
import com.example.moviepagination.data.mapper.MovieInfoMapper
import com.example.moviepagination.data.mapper.MovieItemListMapper
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.repository.ILocalRepo

class LocalRepoImpl(private val movieItemListDao: MovieItemListDao) : ILocalRepo {

    private val movieItemListMapper = MovieItemListMapper()
    private val movieInfoMapper = MovieInfoMapper()

    override suspend fun saveMovieList(list: MovieItemList) {
        return movieItemListDao.insertAllMovieList(movieItemListMapper.mapListEntityToDbModel(list))
    }

    override suspend fun saveMovie(movie: MovieInfo) {
        return movieItemListDao.insertMovieToMyList(
            movieInfoMapper.mapMovieInfoEntityToDbModel(
                movie
            )
        )
    }
//
//    override fun getAllSavedMovieList(): LiveData<MovieItemList> {
//        return Transformations.map(movieItemListDao.getMovieList()){
//            movieItemListMapper.mapDbModelToEntity(it)
//        }
//    }
//
//    override fun getSavedMovieInfo(movieId: String): LiveData<MovieInfo> {
//        return Transformations.map(movieItemListDao.getSavedMovieInfo(movieId)) {
//            movieInfoMapper.mapMovieInfoDbModelToEntity(it)
//        }
//    }

    override suspend fun getAllSavedMovieList(): List<MovieInfo> {
        val list = movieItemListDao.getMovieList()
        return movieInfoMapper.mapMovieInfoListDbModelToListEntity(list)
    }

    override suspend fun getSavedMovieInfo(movieId: String?): MovieInfo {
        val movie = movieItemListDao.getSavedMovieInfo(movieId)
        return movieInfoMapper.mapMovieInfoDbModelToEntity(movie)
    }

    override suspend fun deleteMovieFromMyList(id: String?) {
        return movieItemListDao.deleteMovieFromMyList(id)
    }
}