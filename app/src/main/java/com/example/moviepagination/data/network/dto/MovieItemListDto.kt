package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class MovieItemListDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("items")
    val items: List<ItemDto>,
)
