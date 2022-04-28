package com.example.moviepagination.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviepagination.model.room.dao.MovieItemListDao
import com.example.moviepagination.model.room.entities.MovieItemListEntity
import com.example.moviepagination.utils.Converter

@Database(entities = [MovieItemListEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieItemListDao: MovieItemListDao

    companion object {
        private var db: MovieDataBase? = null
        private const val DB_NAME = "movie.db"
        private val LOCK = Any()

        fun getInstance(context: Context): MovieDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        MovieDataBase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }
}