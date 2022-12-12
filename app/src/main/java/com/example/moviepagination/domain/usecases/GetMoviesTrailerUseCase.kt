package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetMoviesTrailerUseCase(private val repository: IRepository) {
    suspend operator fun invoke(movieId: String) = repository.getMovieTrailerById(movieId)
}