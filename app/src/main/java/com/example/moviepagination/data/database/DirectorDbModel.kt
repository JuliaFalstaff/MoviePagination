package com.example.moviepagination.data.database

import androidx.room.Entity

@Entity
data class DirectorDbModel(
    val id: String,
    val name: String
)
