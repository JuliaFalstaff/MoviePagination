package com.example.moviepagination.domain.entities.castInfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KnownFor(
    @SerializedName("id")
    var id: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("fullTitle")
    var fullTitle: String?,
    @SerializedName("year")
    var year: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("image")
    var image: String?,
) : Parcelable

