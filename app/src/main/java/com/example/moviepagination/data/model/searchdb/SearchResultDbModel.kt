package com.example.moviepagination.data.model.searchdb

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class SearchResultDbModel(
    @SerializedName("searchType")
        var searchType: String? = null,

    @SerializedName("expression")
        var expression: String? = null,

    @SerializedName("results")
        var results: List<ResultDbModel>? = null,

    @SerializedName("errorMessage")
        var errorMessage: String? = null,
)

