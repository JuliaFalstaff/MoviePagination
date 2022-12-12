package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.network.dto.DirectorDto
import com.example.moviepagination.data.network.dto.GenreDto
import com.example.moviepagination.data.network.dto.MovieItemListDto
import com.example.moviepagination.data.network.dto.StarDto
import com.example.moviepagination.domain.entities.*

class MovieItemListMapper {

    fun mapMovieItemListDtoToEntity(movieItemListDto: MovieItemListDto): MovieItemList {
        return MovieItemList(
            items = movieItemListDto.items.map { itemDto ->
                Item(
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
        private const val EMPTY_STRING = ""
        private const val EMPTY_RATING = "0.0"
    }
}
