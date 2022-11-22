package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IMovieRepository

class GetAllMovies(private val repository: IMovieRepository) {
    operator fun invoke() = repository.getAllMovieList()
}