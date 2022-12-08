package com.example.moviepagination.data.network.dto.castinfodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class KnownForDto(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("fullTitle")
    var fullTitle: String? = "",
    @SerializedName("year")
    var year: String? = "",
    @SerializedName("role")
    var role: String? = "",
    @SerializedName("image")
    var image: String? = "",
)

