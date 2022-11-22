package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.database.*
import com.example.moviepagination.data.network.model.DirectorDto
import com.example.moviepagination.data.network.model.GenreDto
import com.example.moviepagination.data.network.model.MovieItemListDto
import com.example.moviepagination.data.network.model.StarDto
import com.example.moviepagination.domain.entities.*

class Mapper {

    fun mapListDtoToListDbModel(movieItemListDto: MovieItemListDto): MovieItemListDbModel {
        return MovieItemListDbModel(
            id = movieItemListDto.id,
            items = movieItemListDto.items.map { itemDto ->
                ItemDbModel(
                    id = itemDto.id,
                    title = itemDto.title,
                    fullTitle = itemDto.fullTitle,
                    year = itemDto.year,
                    releaseState = itemDto.releaseState,
                    image = itemDto.image,
                    runtimeMins = itemDto.runtimeMins,
                    runtimeStr = itemDto.runtimeStr,
                    plot = itemDto.plot,
                    contentRating = itemDto.contentRating,
                    imDbRating = itemDto.imDbRating,
                    imDbRatingCount = itemDto.imDbRatingCount,
                    metacriticRating = itemDto.metacriticRating,
                    genres = itemDto.genres,
                    genreList = convertGenreListDtoToGenreListModel(itemDto.genreList),
                    directors = itemDto.directors,
                    directorList = convertDirectorListDtoToDirectorListModel(itemDto.directorList),
                    stars = itemDto.stars,
                    starList = convertStarListDtoToStarListModel(itemDto.starList)
                )
            }
        )
    }

    fun mapDbModelToEntity(movieItemListDbModel: MovieItemListDbModel): MovieItemList {
        return MovieItemList(
            items = movieItemListDbModel.items.map { itemDbModel ->
                Item (
                    id = itemDbModel.id,
                    title = itemDbModel.title,
                    fullTitle = itemDbModel.fullTitle,
                    year = itemDbModel.year,
                    releaseState = itemDbModel.releaseState,
                    image = itemDbModel.image,
                    runtimeMins = itemDbModel.runtimeMins,
                    runtimeStr = itemDbModel.runtimeStr,
                    plot = itemDbModel.plot,
                    contentRating = itemDbModel.contentRating,
                    imDbRating = itemDbModel.imDbRating,
                    imDbRatingCount = itemDbModel.imDbRatingCount,
                    metacriticRating = itemDbModel.metacriticRating,
                    genres = itemDbModel.genres,
                    genreList = convertGenreListDbModelToGenreListEntity(itemDbModel.genreList),
                    directors = itemDbModel.directors,
                    directorList = convertDirectorListDbModelToDirectorListEntity(itemDbModel.directorList),
                    stars = itemDbModel.stars,
                    starList = convertStarListDbModelToStarEntity(itemDbModel.starList)
                )
            }
        )
    }

    private fun convertGenreListDtoToGenreListModel(genreListDto: List<GenreDto>?): List<GenreDbModel>? {
        return genreListDto?.map {
            GenreDbModel(
                key = it.key,
                value = it.value
            )
        }
    }

    private fun convertDirectorListDtoToDirectorListModel(directorListDto: List<DirectorDto>?): List<DirectorDbModel>? {
        return directorListDto?.map {
            DirectorDbModel(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertStarListDtoToStarListModel(starListDto: List<StarDto>?): List<StarDbModel>? {
        return starListDto?.map {
            StarDbModel(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertGenreListDbModelToGenreListEntity(genreList: List<GenreDbModel>?): List<Genre>? {
        return genreList?.map {
            Genre(
                key = it.key,
                value = it.value
            )
        }
    }

    private fun convertDirectorListDbModelToDirectorListEntity(directorList: List<DirectorDbModel>?): List<Director>? {
        return directorList?.map {
            Director(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertStarListDbModelToStarEntity(starListDbModel: List<StarDbModel>?): List<Star>? {
        return starListDbModel?.map {
            Star(
                id = it.id,
                name = it.name
            )
        }
    }


}
