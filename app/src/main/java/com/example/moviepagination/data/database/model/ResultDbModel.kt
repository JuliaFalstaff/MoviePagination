package com.example.moviepagination.data.database.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ResultDbModel(
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
