package com.example.moviepagination.data.network.model.searchdb

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class SearchResultDto(
    @SerializedName("searchType")
        var searchType: String? = null,

    @SerializedName("expression")
        var expression: String? = null,

    @SerializedName("results")
        var results: List<ResultDto>? = null,

    @SerializedName("errorMessage")
        var errorMessage: String? = null,
)

