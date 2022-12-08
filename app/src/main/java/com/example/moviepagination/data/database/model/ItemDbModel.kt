package com.example.moviepagination.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepagination.data.converters.DirectorConverter
import com.example.moviepagination.data.converters.GenreConverter
import com.example.moviepagination.data.converters.StarConverter

@Entity
data class ItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val primaryId: Int,
    val id: String?,
    val title: String?,
    val fullTitle: String?,
    val year: String?,
    val releaseState: String?,
    val image: String?,
    val runtimeMins: String?,
    val runtimeStr: String?,
    val plot: String?,
    val contentRating: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
    val metacriticRating: String?,
    val genres: String?,
    @TypeConverters(GenreConverter::class)
    val genreList: List<GenreDbModel>? = null,
    val directors: String?,
    @TypeConverters(DirectorConverter::class)
    val directorList: List<DirectorDbModel>? = null,
    val stars: String?,
    @TypeConverters(StarConverter::class)
    val starList: List<StarDbModel>? = null,
)
