package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetMoviesTrailerUseCase(private val repository: IRemoteRepo) {
    operator fun invoke(movieId: String) = repository.getMovieTrailerById(movieId)
}