package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ItemDto(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("fullTitle")
    val fullTitle: String? = "",
    @SerializedName("year")
    val year: String? = "",
    @SerializedName("releaseState")
    val releaseState: String? = "",
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
    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String? = "",
    @SerializedName("metacriticRating")
    val metacriticRating: String? = "",
    @SerializedName("genres")
    val genres: String? = "",
    @SerializedName("genreList")
    val genreList: List<GenreDto>? = null,
    @SerializedName("directors")
    val directors: String? = "",
    @SerializedName("directorList")
    val directorList: List<DirectorDto>? = null,
    @SerializedName("stars")
    val stars: String? = "",
    @SerializedName("starList")
    val starList: List<StarDto>? = null,
)
