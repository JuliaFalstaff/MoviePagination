package com.example.moviepagination.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviepagination.data.database.model.ItemDbModel
import com.example.moviepagination.data.database.model.MovieInfoDbModel
import com.example.moviepagination.data.database.model.MovieItemListDbModel

@Dao
interface MovieItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovieList(list: MovieItemListDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieToMyList(movie: ItemDbModel)

    @Query("SELECT * FROM movie_list")
    fun getMovieList(): LiveData<MovieItemListDbModel>

    @Query("SELECT * FROM movie_info WHERE id=:movieId LIMIT 1")
    fun getSavedMovieInfo(movieId: String): LiveData<MovieInfoDbModel>
}