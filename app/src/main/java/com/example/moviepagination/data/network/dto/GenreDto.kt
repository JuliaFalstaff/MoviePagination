package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class GenreDto(
    @SerializedName("key")
    val key: String = "",
    @SerializedName("value")
    val value: String = ""
)
