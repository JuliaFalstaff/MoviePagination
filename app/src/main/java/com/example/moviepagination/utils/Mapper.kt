package com.example.moviepagination.utils

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.room.entities.MovieItemListEntity

fun convertFromDataToEntity(list: MovieItemList): MovieItemListEntity {
    return MovieItemListEntity(id = 0, list.items)
}

fun convertFromEntityToMovieList(list: MovieItemListEntity): MovieItemList {
    return MovieItemList(list.items)
}

