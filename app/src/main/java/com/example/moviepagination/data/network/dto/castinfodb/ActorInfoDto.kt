package com.example.moviepagination.data.network.dto.castinfodb

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ActorInfoDto(
    @SerializedName("id")
        val id: String? = "",
    @SerializedName("name")
        val name: String? = "",
    @SerializedName("role")
        val role: String? = "",
    @SerializedName("image")
        val image: String? = "",
    @SerializedName("summary")
        val summary: String? = "",
    @SerializedName("birthDate")
        val birthDate: String? = "",
    @SerializedName("deathDate")
        val deathDate: String? = "",
    @SerializedName("awards")
        val awards: String? = "",
    @SerializedName("height")
        val height: String? = "",
    @SerializedName("knownFor")
        val knownFor: List<KnownForDto>? = null,
    @SerializedName("castMovies")
        val castMovies: List<CastMovieDto>? = null,
    @SerializedName("errorMessage")
        val errorMessage: String? = ""
        )
