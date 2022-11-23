package com.example.moviepagination.data.network.dto

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.DirectorConverter
import com.example.moviepagination.data.converters.GenreConverter
import com.example.moviepagination.data.converters.StarConverter

@Entity
data class ItemDto(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val releaseState: String,
    val image: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val contentRating: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val metacriticRating: String,
    val genres: String,
    val genreList: List<GenreDto>? = null,
    val directors: String,
    val directorList: List<DirectorDto>? = null,
    val stars: String,
    val starList: List<StarDto>? = null,
)
