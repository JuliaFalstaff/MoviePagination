package com.example.moviepagination.domain.usecases

import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.repository.ILocalRepo
import com.example.moviepagination.domain.repository.IRemoteRepo

class AddMovieToMyList(private val repository: ILocalRepo) {

    operator fun invoke(movie: Item) = repository.saveMovie(movie)
}