package com.example.moviepagination.data.network.model

import androidx.room.Entity

@Entity
data class StarDto(
    val id: String,
    val name: String
)