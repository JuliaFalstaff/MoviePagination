package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.ILocalRepo

class GetSavedMovieByIdUseCase(private val repository: ILocalRepo) {
    operator fun invoke(movieId: String?) = repository.getSavedMovieInfo(movieId)
}