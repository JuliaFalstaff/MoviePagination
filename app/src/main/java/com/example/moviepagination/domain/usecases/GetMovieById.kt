package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IMovieRepository

class GetMovieById(private val repository: IMovieRepository) {
    operator fun invoke(movieId: String) = repository.getMovieByIdFromServer(movieId)
}