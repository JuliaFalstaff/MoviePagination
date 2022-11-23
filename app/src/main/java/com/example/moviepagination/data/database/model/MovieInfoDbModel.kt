package com.example.moviepagination.data.database.model

import androidx.room.Entity
import com.example.moviepagination.domain.entities.Genre
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_info")
data class MovieInfoDbModel(
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
        val directorList: List<DirectorDbModel>? = null,
    @SerializedName("stars")
        val stars: String = "",
    @SerializedName("starList")
        val starList: List<StarDbModel>? = null,
    @SerializedName("actorList")
        val actorList: List<ActorDbModel>? = null,
    @SerializedName("trailer")
        val trailer: TrailerDbModel
)
