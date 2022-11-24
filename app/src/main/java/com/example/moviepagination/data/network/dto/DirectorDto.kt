package com.example.moviepagination.data.network.dto

import androidx.room.Entity

@Entity
data class DirectorDto(
    val id: String? = "",
    val name: String = ""
)
