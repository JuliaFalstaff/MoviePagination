package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.repository.IMovieRepository

class GetActorInfoById(private val repository: IMovieRepository) {

    operator fun invoke(actorId: String) = repository.getActorInfoById(actorId)
}