package com.example.moviepagination.domain.entities.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(
    @SerializedName("searchType")
    var searchType: String?,
    @SerializedName("expression")
    var expression: String?,
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("errorMessage")
    var errorMessage: String?,
) : Parcelable

