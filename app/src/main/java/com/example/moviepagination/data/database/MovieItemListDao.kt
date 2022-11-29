package com.example.moviepagination.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviepagination.data.database.model.MovieInfoDbModel
import com.example.moviepagination.data.database.model.MovieItemListDbModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovieList(list: MovieItemListDbModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieToMyList(movie: MovieInfoDbModel): Completable

    @Query("SELECT * FROM movie_info")
    fun getMovieList(): Single<List<MovieInfoDbModel>>

    @Query("SELECT * FROM movie_info WHERE id=:movieId LIMIT 1")
    fun getSavedMovieInfo(movieId: String?): Single<MovieInfoDbModel>

    @Query("DELETE FROM movie_info WHERE id=:id")
    fun deleteMovieFromMyList(id: String?): Completable
}