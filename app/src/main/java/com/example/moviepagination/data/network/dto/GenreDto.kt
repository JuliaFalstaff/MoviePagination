package com.example.moviepagination.data.network.dto

import androidx.room.Entity

@Entity
data class GenreDto(
    val key: String,
    val value: String
)
