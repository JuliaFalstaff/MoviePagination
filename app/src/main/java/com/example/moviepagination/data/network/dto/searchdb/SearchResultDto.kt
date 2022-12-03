package com.example.moviepagination.data.network.dto.searchdb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class SearchResultDto(
    @SerializedName("searchType")
    var searchType: String? = "",
    @SerializedName("expression")
    var expression: String? = "",
    @SerializedName("results")
    var results: List<ResultDto>? = null,
    @SerializedName("errorMessage")
    var errorMessage: String? = "",
)

