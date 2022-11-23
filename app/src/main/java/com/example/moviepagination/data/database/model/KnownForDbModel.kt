package com.example.moviepagination.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class KnownForDbModel(
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
)

