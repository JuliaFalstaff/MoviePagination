package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.repository.IRemoteRepo

class GetSearchListUseCase(private val repository: IRemoteRepo) {
    operator fun invoke(expression: String) = repository.getSearchList(expression)
}