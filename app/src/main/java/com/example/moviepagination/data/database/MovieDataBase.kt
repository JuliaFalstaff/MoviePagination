package com.example.moviepagination.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviepagination.data.network.model.MovieItemListDto
import com.example.moviepagination.utils.Converter

@Database(entities = [MovieItemListDbModel::class], version = 2, exportSchema = false)
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
                    )
                            .fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }
}