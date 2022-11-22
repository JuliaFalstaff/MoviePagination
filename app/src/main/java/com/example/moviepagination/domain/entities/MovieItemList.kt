package com.example.moviepagination.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItemList(
        @SerializedName("items")
        val items: List<Item>
): Parcelable
