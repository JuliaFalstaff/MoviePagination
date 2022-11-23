package com.example.moviepagination.data.network.dto.infodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class YouTubeTrailerDto(
    @SerializedName("imDbId")
    var imDbId: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("fullTitle")
    var fullTitle: String? = "",
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("year")
    var year: String? = "",
    @SerializedName("videoId")
    var videoId: String? = "",
    @SerializedName("videoUrl")
    var videoUrl: String? = "",
    @SerializedName("errorMessage")
    var errorMessage: String? = ""
)