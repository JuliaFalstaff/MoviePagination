package com.example.moviepagination.data.network.model.searchdb

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class ResultDto(
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
)
