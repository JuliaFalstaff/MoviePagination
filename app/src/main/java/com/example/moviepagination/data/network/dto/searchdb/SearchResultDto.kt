package com.example.moviepagination.data.network.dto.searchdb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

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

