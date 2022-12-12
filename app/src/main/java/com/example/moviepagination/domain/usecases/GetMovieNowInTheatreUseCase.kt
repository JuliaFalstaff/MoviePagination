package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetMovieNowInTheatreUseCase(private val repository: IRepository) {
    suspend operator fun invoke() = repository.getMovieNowInTheatre()
}