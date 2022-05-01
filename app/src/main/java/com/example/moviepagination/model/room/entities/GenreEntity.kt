package com.example.moviepagination.model.room.entities

import androidx.room.Entity

@Entity
data class GenreEntity(
    val key: String,
    val value: String
)
