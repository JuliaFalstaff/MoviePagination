package com.example.moviepagination.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.*
import com.example.moviepagination.data.database.model.MovieInfoDbModel

@Database(
    entities = [MovieInfoDbModel::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    StarConverter::class,
    DirectorConverter::class,
    GenreConverter::class,
    ActorConverter::class
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieItemListDao(): MovieItemListDao
}