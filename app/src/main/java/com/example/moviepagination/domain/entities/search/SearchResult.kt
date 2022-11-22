package com.example.moviepagination.domain.entities.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    @SerializedName("searchType")
        var searchType: String? = null,

    @SerializedName("expression")
        var expression: String? = null,

    @SerializedName("results")
        var results: List<Result>? = null,

    @SerializedName("errorMessage")
        var errorMessage: String? = null,
) : Parcelable

