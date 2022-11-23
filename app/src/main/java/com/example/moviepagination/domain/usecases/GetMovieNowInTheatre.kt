package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetMovieNowInTheatre(private val repository: IRemoteRepo) {
    operator fun invoke() = repository.getMovieNowInTheatre()
}