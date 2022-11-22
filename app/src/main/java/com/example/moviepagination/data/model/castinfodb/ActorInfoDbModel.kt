package com.example.moviepagination.data.model.castinfodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ActorInfoDbModel(
    @SerializedName("id")
        val id: String = "",
    @SerializedName("name")
        val name: String = "",
    @SerializedName("role")
        val role: String = "",
    @SerializedName("image")
        val image: String = "",
    @SerializedName("summary")
        val summary: String = "",
    @SerializedName("birthDate")
        val birthDate: String = "",
    @SerializedName("deathDate")
        val deathDate: String = "",
    @SerializedName("awards")
        val awards: String = "",
    @SerializedName("height")
        val height: String = "",
    @SerializedName("knownFor")
        val knownFor: List<KnownForDbModel>? = null,
    @SerializedName("castMovies")
        val castMovies: List<CastMovieDbModel>? = null,
    @SerializedName("errorMessage")
        val errorMessage: String = ""
        )
