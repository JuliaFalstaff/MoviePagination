package com.example.moviepagination.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.info.MovieInfo

interface ILocalRepo {
    fun saveMovieList(list: MovieItemList)
    fun saveMovie(movie: Item)
    fun getAllSavedMovieList(): LiveData<MovieItemList>
    fun getSavedMovieInfo(movieId: String): LiveData<MovieInfo>
}