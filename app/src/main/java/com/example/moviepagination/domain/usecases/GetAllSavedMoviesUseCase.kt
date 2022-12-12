package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetAllSavedMoviesUseCase(private val repository: IRepository) {
    suspend operator fun invoke() = repository.getAllSavedMovieList()
}