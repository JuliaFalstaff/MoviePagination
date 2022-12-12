package com.example.moviepagination.domain.usecases


import com.example.moviepagination.domain.repository.IRepository

class GetActorInfoByIdUseCase(private val repository: IRepository) {

    suspend operator fun invoke(actorId: String) = repository.getActorInfoById(actorId)
}