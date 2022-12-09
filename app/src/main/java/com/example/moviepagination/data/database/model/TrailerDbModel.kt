package com.example.moviepagination.data.database.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class TrailerDbModel(
    @SerializedName("imDbId")
    var imDbId: String? = null,

    @SerializedName("title")
    var titleTrailer: String? = null,

    @SerializedName("fullTitle")
    var fullTitleTrailer: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("year")
    var yearTrailer: String? = null,

    @SerializedName("videoId")
    var videoId: String? = null,

    @SerializedName("videoTitle")
    var videoTitle: String? = null,

    @SerializedName("videoDescription")
    var videoDescription: String? = null,

    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = null,

    @SerializedName("link")
    var link: String? = null,

    @SerializedName("linkEmbed")
    var linkEmbed: String? = null,

    @SerializedName("errorMessage")
    var errorMessage: String? = null
)

