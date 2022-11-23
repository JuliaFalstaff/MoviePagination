package com.example.moviepagination.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreDbModel(
    @PrimaryKey
    val key: String,
    val value: String
)
