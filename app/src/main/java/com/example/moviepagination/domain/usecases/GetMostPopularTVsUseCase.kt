package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetMostPopularTVsUseCase(private val repository: IRemoteRepo) {
    suspend operator fun invoke() = repository.getMostPopularTVs()
}