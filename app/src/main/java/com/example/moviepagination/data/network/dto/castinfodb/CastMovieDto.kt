package com.example.moviepagination.data.network.dto.castinfodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class CastMovieDto(
        @SerializedName("id")
        val id: String? = "",
        @SerializedName("role")
        val role: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("year")
        val year: String = "",
        @SerializedName("description")
        val description: String = ""
        )


