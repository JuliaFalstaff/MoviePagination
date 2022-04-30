package com.example.moviepagination.model.room.entities

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.moviepagination.model.data.Director
import com.example.moviepagination.model.data.Genre
import com.example.moviepagination.model.data.Star
import com.example.moviepagination.utils.Converter

@Entity
data class ItemEntity(
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
        @TypeConverters(Converter::class)
        val genreList: List<Genre>? = null,
        val directors: String,
        @TypeConverters(Converter::class)
        val directorList: List<Director>? = null,
        val stars: String,
        @TypeConverters(Converter::class)
        val starList: List<Star>? = null,
)
