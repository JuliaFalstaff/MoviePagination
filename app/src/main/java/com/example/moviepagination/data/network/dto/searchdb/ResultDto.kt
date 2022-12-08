package com.example.moviepagination.data.network.dto.searchdb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ResultDto(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("resultType")
    var resultType: String? = "",
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("description")
    var description: String? = ""
)
