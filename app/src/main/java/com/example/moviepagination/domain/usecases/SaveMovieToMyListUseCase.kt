package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.repository.IRepository

class SaveMovieToMyListUseCase(private val repository: IRepository) {
    suspend operator fun invoke(movie: MovieInfo) = repository.saveMovie(movie)
}