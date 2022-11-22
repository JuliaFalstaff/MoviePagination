package com.example.moviepagination.utils

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.data.model.MovieItemListDbModel

fun convertFromDataToEntity(list: MovieItemList): MovieItemListDbModel {
    return MovieItemListDbModel(id = 0, list.items)
}

fun convertFromEntityToMovieList(list: MovieItemListDbModel): MovieItemList {
    return MovieItemList(list.items)
}