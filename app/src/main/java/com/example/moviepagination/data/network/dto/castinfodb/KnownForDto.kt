package com.example.moviepagination.data.network.dto.castinfodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class KnownForDto(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("fullTitle")
        var fullTitle: String? = null,
        @SerializedName("year")
        var year: String? = null,
        @SerializedName("role")
        var role: String? = null,
        @SerializedName("image")
        var image: String? = null,
)

