package com.example.moviepagination.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.ItemConverter

@Entity(tableName = "movie_list")
data class MovieItemListDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(ItemConverter::class)
    val items: List<ItemDbModel>,
)
