package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetMostPopularTVsUseCase(private val repository: IRepository) {
    suspend operator fun invoke() = repository.getMostPopularTVs()
}