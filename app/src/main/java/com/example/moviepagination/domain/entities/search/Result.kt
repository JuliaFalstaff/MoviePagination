package com.example.moviepagination.domain.entities.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
        @SerializedName("id")
        var id: String?,
        @SerializedName("resultType")
        var resultType: String?,
        @SerializedName("image")
        var image: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("description")
        var description: String?
) : Parcelable
