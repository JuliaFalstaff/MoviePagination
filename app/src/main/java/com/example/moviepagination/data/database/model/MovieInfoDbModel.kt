package com.example.moviepagination.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.ActorConverter
import com.example.moviepagination.data.converters.DirectorConverter
import com.example.moviepagination.data.converters.GenreConverter
import com.example.moviepagination.data.converters.StarConverter
import com.example.moviepagination.domain.entities.Genre

@Entity(tableName = "movie_info")
data class MovieInfoDbModel(
    @PrimaryKey
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val releaseDate: String,
    val image: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val contentRating: String,
    val imDbRating: String,
    val metacriticRating: String,
    val genres: String,
    @TypeConverters(GenreConverter::class)
    val genreList: List<GenreDbModel>?,
    val directors: String,
    @TypeConverters(DirectorConverter::class)
    val directorList: List<DirectorDbModel>? = null,
    val stars: String,
    @TypeConverters(StarConverter::class)
    val starList: List<StarDbModel>?= null,
    @TypeConverters(ActorConverter::class)
    val actorList: List<ActorDbModel>?= null,
    @Embedded
    val trailer: TrailerDbModel
)
