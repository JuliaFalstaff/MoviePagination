package com.example.moviepagination.data.network.dto.infodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class TrailerDto(
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
    @SerializedName("videoTitle")
    var videoTitle: String? = "",
    @SerializedName("videoDescription")
    var videoDescription: String? = "",
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = "",
    @SerializedName("link")
    var link: String? = "",
    @SerializedName("linkEmbed")
    var linkEmbed: String? = "",
    @SerializedName("errorMessage")
    var errorMessage: String? = ""
)

