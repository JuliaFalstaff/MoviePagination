package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.ILocalRepo

class GetAllSavedMovies(private val repository: ILocalRepo) {
    operator fun invoke() = repository.getAllMovieList()
}