package com.example.moviepagination.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviepagination.data.database.model.MovieInfoDbModel

@Dao
interface MovieItemListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieToMyList(movie: MovieInfoDbModel)

    @Query("SELECT * FROM movie_info")
    suspend fun getMovieList(): List<MovieInfoDbModel>

    @Query("SELECT * FROM movie_info WHERE id=:movieId LIMIT 1")
    suspend fun getSavedMovieInfo(movieId: String?): MovieInfoDbModel

    @Query("DELETE FROM movie_info WHERE id=:id")
    suspend fun deleteMovieFromMyList(id: String?)
}