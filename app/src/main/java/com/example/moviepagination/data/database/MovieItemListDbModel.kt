package com.example.moviepagination.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.utils.Converter

@Entity(tableName = "movie_list")
data class MovieItemListDbModel(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @TypeConverters(Converter::class)
        val items: List<ItemDbModel>,
)
