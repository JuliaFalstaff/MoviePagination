package com.example.moviepagination.data.model

import androidx.room.Entity

@Entity
data class GenreDbModel(
    val key: String,
    val value: String
)
