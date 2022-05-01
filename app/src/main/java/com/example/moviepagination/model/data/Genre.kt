package com.example.moviepagination.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: String
): Parcelable
