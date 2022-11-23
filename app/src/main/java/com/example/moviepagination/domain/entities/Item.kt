package com.example.moviepagination.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    @SerializedName("id")
        val id: String,
    @SerializedName("title")
        val title: String,
    @SerializedName("fullTitle")
        val fullTitle: String,
    @SerializedName("year")
        val year: String,
    @SerializedName("releaseState")
        val releaseState: String,
    @SerializedName("image")
        val image: String,
    @SerializedName("runtimeMins")
        val runtimeMins: String,
    @SerializedName("runtimeStr")
        val runtimeStr: String,
    @SerializedName("plot")
        val plot: String,
    @SerializedName("contentRating")
        val contentRating: String,
    @SerializedName("imDbRating")
        val imDbRating: String,
    @SerializedName("imDbRatingCount")
        val imDbRatingCount: String,
    @SerializedName("metacriticRating")
        val metacriticRating: String,
    @SerializedName("genres")
        val genres: String,
    @SerializedName("genreList")
        val genreList: List<Genre>?,
    @SerializedName("directors")
        val directors: String,
    @SerializedName("directorList")
        val directorList: List<Director>?,
    @SerializedName("stars")
        val stars: String,
    @SerializedName("starList")
        val starList: List<Star>?
) : Parcelable
