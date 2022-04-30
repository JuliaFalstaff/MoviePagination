package com.example.moviepagination.model.data.info

import android.os.Parcelable
import com.example.moviepagination.model.data.Director
import com.example.moviepagination.model.data.Genre
import com.example.moviepagination.model.data.Star
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfo(
        @SerializedName("id")
        val id: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("fullTitle")
        val fullTitle: String = "",
        @SerializedName("year")
        val year: String = "",
        @SerializedName("releaseDate")
        val releaseDate: String = "",
        @SerializedName("image")
        val image: String = "",
        @SerializedName("runtimeMins")
        val runtimeMins: String = "",
        @SerializedName("runtimeStr")
        val runtimeStr: String = "",
        @SerializedName("plot")
        val plot: String = "",
        @SerializedName("contentRating")
        val contentRating: String = "",
        @SerializedName("imDbRating")
        val imDbRating: String = "",
        @SerializedName("metacriticRating")
        val metacriticRating: String = "",
        @SerializedName("genres")
        val genres: String = "",
        @SerializedName("genreList")
        val genreList: List<Genre>? = null,
        @SerializedName("directors")
        val directors: String = "",
        @SerializedName("directorList")
        val directorList: List<Director>? = null,
        @SerializedName("stars")
        val stars: String = "",
        @SerializedName("starList")
        val starList: List<Star>? = null,
        @SerializedName("actorList")
        val actorList: List<Actor>? = null
) : Parcelable