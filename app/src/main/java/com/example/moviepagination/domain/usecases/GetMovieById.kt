package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetMovieById(private val repository: IRemoteRepo) {
    operator fun invoke(movieId: String) = repository.getMovieByIdFromServer(movieId)
}