package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetComingSoonMovieUseCase(private val repository: IRemoteRepo) {
    operator fun invoke() = repository.getComingSoonMoviesFromServer()
}