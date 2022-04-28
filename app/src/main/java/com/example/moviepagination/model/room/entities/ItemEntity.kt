package com.example.moviepagination.model.room.entities

import androidx.room.Entity

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
//        val genreList: List<GenreEntity>? = null,
        val directors: String,
//        val directorList: List<DirectorEntity>? = null,
        val stars: String,
//        val starList: List<StarEntity>? = null
)
