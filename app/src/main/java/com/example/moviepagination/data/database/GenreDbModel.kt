package com.example.moviepagination.data.database

import androidx.room.Entity

@Entity
data class GenreDbModel(
    val key: String,
    val value: String
)
