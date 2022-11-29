package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.ILocalRepo

class DeleteMovieFromMyList(private val repository: ILocalRepo) {
    operator fun invoke(movieId: String?) = repository.deleteMovieFromMyList(movieId)
}