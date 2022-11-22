package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IMovieRepository

class GetMovieNowInTheatre(private val repository: IMovieRepository) {
    operator fun invoke() = repository.getMovieNowInTheatre()
}