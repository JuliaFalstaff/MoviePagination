package com.example.moviepagination.utils

import com.example.moviepagination.model.data.Item
import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.room.entities.ItemEntity
import com.example.moviepagination.model.room.entities.MovieItemListEntity

fun convertFromDataToEntity(list: MovieItemList): MovieItemListEntity {
    return MovieItemListEntity(id = 0, list.items.map {
        ItemEntity(it.id, it.title, it.fullTitle, it.year, it.releaseState, it.image,
        it.runtimeMins, it.runtimeStr, it.plot, it.contentRating, it.imDbRating, it.imDbRatingCount,
        it.metacriticRating, it.genres, it.directors, it.stars)
    })
}

fun convertFromEntityToMovieList(list: MovieItemListEntity): MovieItemList {
    return MovieItemList(list.items.map {
        Item(it.id, it.title, it.fullTitle, it.year, it.releaseState, it.image,
                it.runtimeMins, it.runtimeStr, it.plot, it.contentRating, it.imDbRating, it.imDbRatingCount,
                it.metacriticRating, it.genres, it.directors, it.stars)
    })
}

