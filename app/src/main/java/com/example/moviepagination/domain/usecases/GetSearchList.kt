package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IMovieRepository

class GetSearchList(private val repository: IMovieRepository) {
    operator fun invoke(expression: String) = repository.getSearchList(expression)
}