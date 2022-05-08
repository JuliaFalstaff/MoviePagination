package com.example.moviepagination.model.data.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("resultType")
        var resultType: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("description")
        var description: String? = null
) : Parcelable
