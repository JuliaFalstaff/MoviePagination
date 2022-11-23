package com.example.moviepagination.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.DirectorConverter
import com.example.moviepagination.data.converters.GenreConverter
import com.example.moviepagination.data.converters.ItemConverter
import com.example.moviepagination.data.converters.StarConverter
import com.example.moviepagination.data.database.model.ItemDbModel
import com.example.moviepagination.data.database.model.MovieItemListDbModel

@Database(
    entities = [MovieItemListDbModel::class, ItemDbModel::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(
    ItemConverter::class,
    StarConverter::class,
    DirectorConverter::class,
    GenreConverter::class
)
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
                    )
                        .fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }
}