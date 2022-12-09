package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetMostPopularMoviesUseCase(private val repository: IRepository) {
    suspend operator fun invoke() = repository.getMostPopularMovies()
}