package com.example.moviepagination.data.repository

import com.example.moviepagination.data.database.MovieItemListDao
import com.example.moviepagination.data.mapper.MovieInfoMapper
import com.example.moviepagination.data.mapper.MovieItemListMapper
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.repository.ILocalRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LocalRepoImpl(private val movieItemListDao: MovieItemListDao) : ILocalRepo {

    private val movieItemListMapper = MovieItemListMapper()
    private val movieInfoMapper = MovieInfoMapper()

    override fun saveMovieList(list: MovieItemList): Completable {
        return movieItemListDao.insertAllMovieList(movieItemListMapper.mapListEntityToDbModel(list))
    }

    override fun saveMovie(movie: MovieInfo): Completable {
        return movieItemListDao.insertMovieToMyList(movieInfoMapper.mapMovieInfoEntityToDbModel(movie))
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

    override fun getAllSavedMovieList(): Single<List<MovieInfo>> {
        return movieItemListDao.getMovieList().map {
            movieInfoMapper.mapMovieInfoListDbModelToListEntity(it)
        }
    }

    override fun getSavedMovieInfo(movieId: String?): Single<MovieInfo> {
        return movieItemListDao.getSavedMovieInfo(movieId).map {
            movieInfoMapper.mapMovieInfoDbModelToEntity(it)
        }
    }

    override fun deleteMovieFromMyList(id: String?): Completable {
        return movieItemListDao.deleteMovieFromMyList(id)
    }
}