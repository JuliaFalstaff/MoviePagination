package com.example.moviepagination.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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