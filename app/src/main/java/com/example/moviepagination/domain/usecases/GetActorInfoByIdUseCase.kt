package com.example.moviepagination.domain.usecases


import com.example.moviepagination.domain.repository.IRemoteRepo

class GetActorInfoByIdUseCase(private val repository: IRemoteRepo) {

    suspend operator fun invoke(actorId: String) = repository.getActorInfoById(actorId)
}