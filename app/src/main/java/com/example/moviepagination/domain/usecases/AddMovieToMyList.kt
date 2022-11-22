package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.repository.IMovieRepository

class AddMovieToMyList(private val repository: IMovieRepository) {

    operator fun invoke(list: MovieItemList) = repository.saveMovieList(list)
}