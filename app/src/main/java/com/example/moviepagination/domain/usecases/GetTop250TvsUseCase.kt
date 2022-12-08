package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetTop250TvsUseCase(private val repository: IRemoteRepo) {
    suspend operator fun invoke() = repository.getTOP250TVs()
}