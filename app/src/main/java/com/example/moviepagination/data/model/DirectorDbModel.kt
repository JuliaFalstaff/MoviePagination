package com.example.moviepagination.data.model

import androidx.room.Entity

@Entity
data class DirectorDbModel(
    val id: String,
    val name: String
)
