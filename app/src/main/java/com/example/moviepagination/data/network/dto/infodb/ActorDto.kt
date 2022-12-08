package com.example.moviepagination.data.network.dto.infodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ActorDto(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image")
    val image: String? ="",
    @SerializedName("name")
    val name: String?="",
    @SerializedName("asCharacter")
    val asCharacter: String?=""
)
