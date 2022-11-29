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
import com.example.moviepagination.domain.entities.Genre
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

    fun mapMovieInfoDbModelToEntity(movieInfoDbModel: MovieInfoDbModel): MovieInfo {
        return MovieInfo(
            id = movieInfoDbModel.id,
            title = movieInfoDbModel.title,
            fullTitle = movieInfoDbModel.fullTitle,
            year = movieInfoDbModel.year,
            releaseDate = movieInfoDbModel.releaseDate,
            image = movieInfoDbModel.image,
            runtimeMins = movieInfoDbModel.runtimeMins,
            runtimeStr = movieInfoDbModel.runtimeStr,
            plot = movieInfoDbModel.plot,
            contentRating = movieInfoDbModel.contentRating,
            imDbRating = movieInfoDbModel.imDbRating,
            metacriticRating = movieInfoDbModel.metacriticRating,
            genres = movieInfoDbModel.genres,
            genreList = convertGenreListDbModelToEntity(movieInfoDbModel.genreList),
            directors = movieInfoDbModel.directors,
            directorList = convertDirectorListDbModelToDirectorListEntity(movieInfoDbModel.directorList),
            stars = movieInfoDbModel.stars,
            starList = convertStarListDbModelToStarEntity(movieInfoDbModel.starList),
            actorList = convertActorListDbModelToEntity(movieInfoDbModel.actorList),
            trailer = convertTrailerDbModelToEntity(movieInfoDbModel.trailer),
            isFavourite = movieInfoDbModel.isFavourite

        )
    }

    fun mapMovieInfoListDbModelToListEntity(movieInfoDbModelList: List<MovieInfoDbModel>): List<MovieInfo> {
        return movieInfoDbModelList.map {
            mapMovieInfoDbModelToEntity(it)
        }
    }

    fun mapMovieInfoEntityToDbModel(movieInfo: MovieInfo): MovieInfoDbModel {
        return MovieInfoDbModel(
            primaryId = 0,
            id = movieInfo.id,
            title = movieInfo.title,
            fullTitle = movieInfo.fullTitle,
            year = movieInfo.year,
            releaseDate = movieInfo.releaseDate,
            image = movieInfo.image,
            runtimeMins = movieInfo.runtimeMins,
            runtimeStr = movieInfo.runtimeStr,
            plot = movieInfo.plot,
            contentRating = movieInfo.contentRating,
            imDbRating = movieInfo.imDbRating,
            metacriticRating = movieInfo.metacriticRating,
            genres = movieInfo.genres,
            genreList = convertGenreListEntityToDbModel(movieInfo.genreList),
            directors = movieInfo.directors,
            directorList = convertDirectorListEntityToDbModel(movieInfo.directorList),
            stars = movieInfo.stars,
            starList = convertStarListEntityToDbModel(movieInfo.starList),
            actorList = convertActorListEntityToDbModel(movieInfo.actorList),
            trailer = convertTrailerEntityToDbModel(movieInfo.trailer),
            isFavourite = movieInfo.isFavourite
        )
    }

    private fun convertTrailerDbModelToEntity(trailerDbModel: TrailerDbModel?): Trailer {
        return Trailer(
            imDbId = trailerDbModel?.imDbId,
            title = trailerDbModel?.titleTrailer,
            fullTitle = trailerDbModel?.fullTitleTrailer,
            year = trailerDbModel?.yearTrailer,
            type = trailerDbModel?.type,
            videoId = trailerDbModel?.videoId,
            videoTitle = trailerDbModel?.videoTitle,
            videoDescription = trailerDbModel?.videoDescription,
            thumbnailUrl = trailerDbModel?.thumbnailUrl,
            link = trailerDbModel?.link,
            linkEmbed = trailerDbModel?.linkEmbed,
            errorMessage = trailerDbModel?.errorMessage
        )
    }

    private fun convertTrailerEntityToDbModel(trailer: Trailer): TrailerDbModel? {
        return TrailerDbModel(
            imDbId = trailer.imDbId,
            titleTrailer = trailer.title,
            fullTitleTrailer = trailer.fullTitle,
            yearTrailer = trailer.year,
            type = trailer.type,
            videoId = trailer.videoId,
            videoTitle = trailer.videoTitle,
            videoDescription = trailer.videoDescription,
            thumbnailUrl = trailer.thumbnailUrl,
            link = trailer.link,
            linkEmbed = trailer.linkEmbed,
            errorMessage = trailer.errorMessage
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

    private fun convertActorListEntityToDbModel(actorList: List<Actor>?): List<ActorDbModel>? {
        return actorList?.map {
            ActorDbModel(
                id = it.id,
                name = it.name,
                image = it.image,
                asCharacter = it.asCharacter
            )
        }
    }

    private fun convertGenreListEntityToDbModel(genreList: List<Genre>?): List<GenreDbModel>? {
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

    private fun convertGenreListDbModelToEntity(genreListDbModel: List<GenreDbModel>?): List<Genre>? {
        return genreListDbModel?.map {
            Genre(
                key = it.key,
                value = it.value
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
