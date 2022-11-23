package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.utils.Converter

@Entity
data class MovieItemListDto(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @TypeConverters(Converter::class)
        val items: List<ItemDto>,
)
