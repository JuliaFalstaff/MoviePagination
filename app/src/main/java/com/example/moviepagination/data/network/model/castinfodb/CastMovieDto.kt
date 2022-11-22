package com.example.moviepagination.data.network.model.castinfodb

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
data class CastMovieDto(
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
        )

