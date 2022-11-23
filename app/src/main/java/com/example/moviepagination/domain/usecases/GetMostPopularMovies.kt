package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetMostPopularMovies(private val repository: IRemoteRepo) {
    operator fun invoke() = repository.getMostPopularMovies()
}