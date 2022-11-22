package com.example.moviepagination.domain.entities.castInfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastMovie(
        @SerializedName("id")
        val id: String,
        @SerializedName("role")
        val role: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("year")
        val year: String,
        @SerializedName("description")
        val description: String
        ) : Parcelable
