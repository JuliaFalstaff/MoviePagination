package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.database.model.*
import com.example.moviepagination.data.network.dto.DirectorDto
import com.example.moviepagination.data.network.dto.GenreDto
import com.example.moviepagination.data.network.dto.MovieItemListDto
import com.example.moviepagination.data.network.dto.StarDto
import com.example.moviepagination.domain.entities.*

class MovieItemListMapper {

    fun mapListDtoToListDbModel(movieItemListDto: MovieItemListDto): MovieItemListDbModel {
        return MovieItemListDbModel(
            id = movieItemListDto.id,
            items = movieItemListDto.items.map { itemDto ->
                ItemDbModel(
                    primaryId = UNDEFINED_ID,
                    id = itemDto.id ,
                    title = itemDto.title ,
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

    fun mapListEntityToDbModel(movieItemList: MovieItemList): MovieItemListDbModel {
        return MovieItemListDbModel(
            id = 0,
            items = movieItemList.items.map { itemDto ->
                ItemDbModel(
                    primaryId = UNDEFINED_ID,
                    id = itemDto.id ?: EMPTY_STRING,
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
                    genreList = convertGenreListEntityToGenreListDbModel(itemDto.genreList),
                    directors = itemDto.directors,
                    directorList = convertDirectorListEntityToDbModel(itemDto.directorList),
                    stars = itemDto.stars,
                    starList = convertStarListEntityToDbModel(itemDto.starList)
                )
            }
        )
    }

    fun mapItemEntityToDbModel(item: Item): ItemDbModel {
        return ItemDbModel(
            primaryId = UNDEFINED_ID,
            id = item.id ?: EMPTY_STRING,
            title = item.title,
            fullTitle = item.fullTitle,
            year = item.year,
            releaseState = item.releaseState,
            image = item.image,
            runtimeMins = item.runtimeMins,
            runtimeStr = item.runtimeStr,
            plot = item.plot,
            contentRating = item.contentRating,
            imDbRating = item.imDbRating,
            imDbRatingCount = item.imDbRatingCount,
            metacriticRating = item.metacriticRating,
            genres = item.genres,
            genreList = convertGenreListEntityToGenreListDbModel(item.genreList),
            directors = item.directors,
            directorList = convertDirectorListEntityToDbModel(item.directorList),
            stars = item.stars,
            starList = convertStarListEntityToDbModel(item.starList)
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

    fun mapMovieItemListDtoToEntity(movieItemListDto: MovieItemListDto): MovieItemList {
        return MovieItemList(
            items = movieItemListDto.items.map { itemDto ->
                Item (
                    id = itemDto.id ?: EMPTY_STRING,
                    title = itemDto.title,
                    fullTitle = itemDto.fullTitle,
                    year = itemDto.year,
                    releaseState = itemDto.releaseState,
                    image = itemDto.image,
                    runtimeMins = itemDto.runtimeMins,
                    runtimeStr = itemDto.runtimeStr,
                    plot = itemDto.plot,
                    contentRating = itemDto.contentRating,
                    imDbRating = itemDto.imDbRating ?: EMPTY_RATING,
                    imDbRatingCount = itemDto.imDbRatingCount,
                    metacriticRating = itemDto.metacriticRating,
                    genres = itemDto.genres,
                    genreList = convertGenreListDtoToGenreListEntity(itemDto.genreList),
                    directors = itemDto.directors,
                    directorList = convertDirectorListDtoToDirectorListEntity(itemDto.directorList),
                    stars = itemDto.stars,
                    starList = convertStarListDtoToStarListEntity(itemDto.starList)
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


    private fun convertGenreListEntityToGenreListDbModel(genreList: List<Genre>?): List<GenreDbModel>? {
        return genreList?.map {
            GenreDbModel(
                key = it.key,
                value = it.value
            )
        }
    }

    private fun convertDirectorListEntityToDbModel(directorList: List<Director>?): List<DirectorDbModel>? {
        return directorList?.map {
            DirectorDbModel(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertStarListEntityToDbModel(starList: List<Star>?): List<StarDbModel>? {
        return starList?.map {
            StarDbModel(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertGenreListDtoToGenreListEntity(genreListDto: List<GenreDto>?): List<Genre>? {
        return genreListDto?.map {
            Genre(
                key = it.key,
                value = it.value
            )
        }
    }

    private fun convertDirectorListDtoToDirectorListEntity(directorListDto: List<DirectorDto>?): List<Director>? {
        return directorListDto?.map {
            Director(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertStarListDtoToStarListEntity(starListDto: List<StarDto>?): List<Star>? {
        return starListDto?.map {
            Star(
                id = it.id,
                name = it.name
            )
        }
    }

    companion object {
        private const val UNDEFINED_ID = 0
        private const val EMPTY_STRING = ""
        private const val EMPTY_RATING = "0.0"
    }
}
