package com.example.moviepagination.data.network.dto.infodb

import androidx.room.Entity
import com.example.moviepagination.data.network.dto.DirectorDto
import com.example.moviepagination.data.network.dto.StarDto
import com.example.moviepagination.domain.entities.Genre
import com.google.gson.annotations.SerializedName

@Entity
data class MovieInfoDto(
    @SerializedName("id")
        val id: String? = "",
    @SerializedName("title")
        val title: String? = "",
    @SerializedName("fullTitle")
        val fullTitle: String? = "",
    @SerializedName("year")
        val year: String? = "",
    @SerializedName("releaseDate")
        val releaseDate: String? = "",
    @SerializedName("image")
        val image: String? = "",
    @SerializedName("runtimeMins")
        val runtimeMins: String? = "",
    @SerializedName("runtimeStr")
        val runtimeStr: String? = "",
    @SerializedName("plot")
        val plot: String? = "",
    @SerializedName("contentRating")
        val contentRating: String? = "",
    @SerializedName("imDbRating")
        val imDbRating: String? = "",
    @SerializedName("metacriticRating")
        val metacriticRating: String? = "",
    @SerializedName("genres")
        val genres: String? = "",
    @SerializedName("genreList")
        val genreList: List<Genre>? = null,
    @SerializedName("directors")
        val directors: String? = "",
    @SerializedName("directorList")
        val directorList: List<DirectorDto>? = null,
    @SerializedName("stars")
        val stars: String? = "",
    @SerializedName("starList")
        val starList: List<StarDto>? = null,
    @SerializedName("actorList")
        val actorList: List<ActorDto>? = null,
    @SerializedName("trailer")
        val trailer: TrailerDto? = null
)
