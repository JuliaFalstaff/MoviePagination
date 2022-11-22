package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IMovieRepository

class GetComingSoonMovie(private val repository: IMovieRepository) {
    operator fun invoke() = repository.getComingSoonMoviesFromServer()
}