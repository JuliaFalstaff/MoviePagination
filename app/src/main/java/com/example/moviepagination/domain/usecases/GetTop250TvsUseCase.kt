package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRepository

class GetTop250TvsUseCase(private val repository: IRepository) {
    suspend operator fun invoke() = repository.getTOP250TVs()
}