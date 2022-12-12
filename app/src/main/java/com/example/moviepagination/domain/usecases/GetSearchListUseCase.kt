package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetSearchListUseCase(private val repository: IRepository) {
    suspend operator fun invoke(expression: String) = repository.getSearchList(expression)
}