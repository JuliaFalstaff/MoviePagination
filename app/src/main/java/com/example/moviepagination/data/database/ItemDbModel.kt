package com.example.moviepagination.data.database

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.moviepagination.domain.entities.Director
import com.example.moviepagination.domain.entities.Genre
import com.example.moviepagination.domain.entities.Star
import com.example.moviepagination.utils.Converter

@Entity
data class ItemDbModel(
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
        val genreList: List<GenreDbModel>? = null,
        val directors: String,
        @TypeConverters(Converter::class)
        val directorList: List<DirectorDbModel>? = null,
        val stars: String,
        @TypeConverters(Converter::class)
        val starList: List<StarDbModel>? = null,
)
