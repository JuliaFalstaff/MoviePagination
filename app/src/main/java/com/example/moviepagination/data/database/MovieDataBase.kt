package com.example.moviepagination.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.*
import com.example.moviepagination.data.database.model.ItemDbModel
import com.example.moviepagination.data.database.model.MovieInfoDbModel
import com.example.moviepagination.data.database.model.MovieItemListDbModel

@Database(
    entities = [MovieItemListDbModel::class, ItemDbModel::class, MovieInfoDbModel::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    ItemConverter::class,
    StarConverter::class,
    DirectorConverter::class,
    GenreConverter::class,
    ActorConverter::class
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieItemListDao(): MovieItemListDao
}