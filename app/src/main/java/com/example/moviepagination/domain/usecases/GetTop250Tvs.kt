package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IMovieRepository

class GetTop250Tvs(private val repository: IMovieRepository) {
    operator fun invoke() = repository.getTOP250TVs()
}