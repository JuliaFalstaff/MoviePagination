package com.example.moviepagination.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.model.data.Item
import com.example.moviepagination.utils.Converter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_list")

data class MovieItemListEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @TypeConverters(Converter::class)
        val items: List<ItemEntity>
)
