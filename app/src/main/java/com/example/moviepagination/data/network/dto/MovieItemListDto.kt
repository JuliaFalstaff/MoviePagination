package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.ItemConverter

@Entity
data class MovieItemListDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val items: List<ItemDto>,
)
