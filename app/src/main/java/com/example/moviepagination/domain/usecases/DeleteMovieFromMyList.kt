package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class DeleteMovieFromMyList(private val repository: IRepository) {
    suspend operator fun invoke(movieId: String?) = repository.deleteMovieFromMyList(movieId)
}