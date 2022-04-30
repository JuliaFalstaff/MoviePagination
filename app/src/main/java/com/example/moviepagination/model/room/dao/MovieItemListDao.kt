package com.example.moviepagination.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviepagination.model.room.entities.MovieItemListEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovieList(list: MovieItemListEntity)

    @Query("SELECT * FROM movie_list")
    fun getMovieList(): Single<MovieItemListEntity>
}