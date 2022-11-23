package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.database.model.DirectorDbModel
import com.example.moviepagination.data.database.model.StarDbModel
import com.example.moviepagination.data.database.model.*
import com.example.moviepagination.data.network.dto.DirectorDto
import com.example.moviepagination.data.network.dto.StarDto
import com.example.moviepagination.data.network.dto.infodb.ActorDto
import com.example.moviepagination.data.network.dto.infodb.MovieInfoDto
import com.example.moviepagination.data.network.dto.infodb.TrailerDto
import com.example.moviepagination.domain.entities.Director
import com.example.moviepagination.domain.entities.Star
import com.example.moviepagination.domain.entities.info.Actor
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.Trailer

class MovieInfoMapper {

    fun mapMovieInfoDtoToEntity(movieInfoDto: MovieInfoDto): MovieInfo {
        return MovieInfo(
            id = movieInfoDto.id,
            title = movieInfoDto.title,
            fullTitle = movieInfoDto.fullTitle,
            year = movieInfoDto.year,
            releaseDate = movieInfoDto.releaseDate,
            image = movieInfoDto.image,
            runtimeMins = movieInfoDto.runtimeMins,
            runtimeStr = movieInfoDto.runtimeStr,
            plot = movieInfoDto.plot,
            contentRating = movieInfoDto.contentRating,
            imDbRating = movieInfoDto.imDbRating,
            metacriticRating = movieInfoDto.metacriticRating,
            genres = movieInfoDto.genres,
            genreList = movieInfoDto.genreList,
            directors = movieInfoDto.directors,
            directorList = convertDirectorListDtoToDirectorListEntity(movieInfoDto.directorList),
            stars = movieInfoDto.stars,
            starList = convertStarListDtoToStarEntity(movieInfoDto.starList),
            actorList = convertActorListDtoToEntity(movieInfoDto.actorList),
            trailer = convertTrailerDtoToEntity(movieInfoDto.trailer)
        )
    }

    private fun convertTrailerDbModelToEntity(trailerDbModel: TrailerDbModel): Trailer {
        return Trailer(
            imDbId = trailerDbModel.imDbId,
            title = trailerDbModel.title,
            fullTitle = trailerDbModel.fullTitle,
            year = trailerDbModel.year,
            type = trailerDbModel.type,
            videoId = trailerDbModel.videoId,
            videoTitle = trailerDbModel.videoTitle,
            videoDescription = trailerDbModel.videoDescription,
            thumbnailUrl = trailerDbModel.thumbnailUrl,
            link = trailerDbModel.link,
            linkEmbed = trailerDbModel.linkEmbed,
            errorMessage = trailerDbModel.errorMessage
        )
    }

    private fun convertTrailerDtoToEntity(trailerDto: TrailerDto?): Trailer {
        return Trailer(
            imDbId = trailerDto?.imDbId,
            title = trailerDto?.title,
            fullTitle = trailerDto?.fullTitle,
            year = trailerDto?.year,
            type = trailerDto?.type,
            videoId = trailerDto?.videoId,
            videoTitle = trailerDto?.videoTitle,
            videoDescription = trailerDto?.videoDescription,
            thumbnailUrl = trailerDto?.thumbnailUrl,
            link = trailerDto?.link,
            linkEmbed = trailerDto?.linkEmbed,
            errorMessage = trailerDto?.errorMessage
        )
    }

    private fun convertTrailerDtoToDbModel(trailerDto: TrailerDto): TrailerDbModel {
        return TrailerDbModel(
            imDbId = trailerDto.imDbId,
            title = trailerDto.title,
            fullTitle = trailerDto.fullTitle,
            year = trailerDto.year,
            type = trailerDto.type,
            videoId = trailerDto.videoId,
            videoTitle = trailerDto.videoTitle,
            videoDescription = trailerDto.videoDescription,
            thumbnailUrl = trailerDto.thumbnailUrl,
            link = trailerDto.link,
            linkEmbed = trailerDto.linkEmbed,
            errorMessage = trailerDto.errorMessage
        )
    }

    private fun convertActorListDtoToEntity(actorListDto: List<ActorDto>?): List<Actor>? {
        return actorListDto?.map {
            Actor(
                id = it.id,
                name = it.name,
                image = it.image,
                asCharacter = it.asCharacter
            )
        }
    }

    private fun convertDirectorListDtoToDirectorListEntity(directorList: List<DirectorDto>?): List<Director>? {
        return directorList?.map {
            Director(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertStarListDtoToStarEntity(starListDto: List<StarDto>?): List<Star>? {
        return starListDto?.map {
            Star(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertActorListDbModelToEntity(actorListDbModel: List<ActorDbModel>?): List<Actor>? {
        return actorListDbModel?.map {
            Actor(
                id = it.id,
                name = it.name,
                image = it.image,
                asCharacter = it.asCharacter
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

    private fun convertActorListDtoToDbModel(actorListDto: List<ActorDto>?): List<ActorDbModel>? {
        return actorListDto?.map {
            ActorDbModel(
                id = it.id,
                name = it.name,
                image = it.image,
                asCharacter = it.asCharacter
            )
        }
    }

    private fun convertDirectorListDtoToDbModel(directorList: List<DirectorDto>?): List<DirectorDbModel>? {
        return directorList?.map {
            DirectorDbModel(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun convertStarListDtoToDbModel(starList: List<StarDto>?): List<StarDbModel>? {
        return starList?.map {
            StarDbModel(
                id = it.id,
                name = it.name
            )
        }
    }
}