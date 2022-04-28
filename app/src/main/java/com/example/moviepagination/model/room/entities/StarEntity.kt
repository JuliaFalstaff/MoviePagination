package com.example.moviepagination.model.room.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class StarEntity(
    val id: String,
    val name: String
)
