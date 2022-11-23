package com.example.moviepagination.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
data class TrailerDbModel (
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

