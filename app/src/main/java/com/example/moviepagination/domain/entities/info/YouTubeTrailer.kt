package com.example.moviepagination.domain.entities.info

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YouTubeTrailer(
    @SerializedName("imDbId")
    var imDbId: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("fullTitle")
    var fullTitle: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("year")
    var year: String?,
    @SerializedName("videoId")
    var videoId: String,
    @SerializedName("videoUrl")
    var videoUrl: String?,
    @SerializedName("errorMessage")
    var errorMessage: String?
) : Parcelable