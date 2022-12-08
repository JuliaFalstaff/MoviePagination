package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class StarDto(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String = ""
)