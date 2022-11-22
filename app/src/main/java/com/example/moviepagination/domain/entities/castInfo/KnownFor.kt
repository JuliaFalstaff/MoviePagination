package com.example.moviepagination.domain.entities.castInfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KnownFor(
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
) : Parcelable

