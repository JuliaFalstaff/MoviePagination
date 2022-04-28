package com.example.moviepagination.model.room.entities

import androidx.room.Entity

@Entity
data class DirectorEntity(
    val id: String,
    val name: String
)
