package com.example.moviepagination.data.database.model

import androidx.room.Entity

@Entity
data class DirectorDbModel(
    val id: String,
    val name: String
)
