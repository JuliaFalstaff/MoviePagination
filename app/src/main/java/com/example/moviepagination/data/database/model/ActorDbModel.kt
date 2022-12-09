package com.example.moviepagination.data.database.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ActorDbModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("asCharacter")
    val asCharacter: String?
)
