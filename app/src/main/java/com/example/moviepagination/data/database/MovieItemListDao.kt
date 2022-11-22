package com.example.moviepagination.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviepagination.data.network.model.MovieItemListDto
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovieList(list: MovieItemListDbModel)

    @Query("SELECT * FROM movie_list")
    fun getMovieList(): Single<MovieItemListDbModel>
}