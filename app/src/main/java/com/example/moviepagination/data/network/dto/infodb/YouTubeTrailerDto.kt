package com.example.moviepagination.data.network.dto.infodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class YouTubeTrailerDto(
    @SerializedName("imDbId")
    var imDbId: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("fullTitle")
    var fullTitle: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("year")
    var year: String? = null,
    @SerializedName("videoId")
    var videoId: String? = null,
    @SerializedName("videoUrl")
    var videoUrl: String? = null,
    @SerializedName("errorMessage")
    var errorMessage: String? = null
)