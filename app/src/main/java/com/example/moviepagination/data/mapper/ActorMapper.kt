package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.network.dto.castinfodb.ActorInfoDto
import com.example.moviepagination.data.network.dto.castinfodb.CastMovieDto
import com.example.moviepagination.data.network.dto.castinfodb.KnownForDto
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.castInfo.CastMovie
import com.example.moviepagination.domain.entities.castInfo.KnownFor

class ActorMapper {

    fun mapActorInfoDtoToEntity(actorInfoDto: ActorInfoDto): ActorInfo {
        return ActorInfo(
            id = actorInfoDto.id,
            name = actorInfoDto.name,
            role = actorInfoDto.role,
            image = actorInfoDto.image,
            summary = actorInfoDto.summary,
            birthDate = actorInfoDto.birthDate,
            deathDate = actorInfoDto.deathDate,
            awards = actorInfoDto.awards,
            height = actorInfoDto.height,
            knownFor = mapListKnownForDbModelToEntity(actorInfoDto.knownFor),
            castMovies = mapListCastMoviesDtoToEntity(actorInfoDto.castMovies),
        )
    }

    private fun convertKnownForDtoToEntity(knownForDto: KnownForDto): KnownFor {
        return KnownFor(
            id = knownForDto.id,
            title = knownForDto.title,
            fullTitle = knownForDto.fullTitle,
            year = knownForDto.year,
            role = knownForDto.role,
            image = knownForDto.image
        )
    }

    private fun mapListKnownForDbModelToEntity(list: List<KnownForDto>?): List<KnownFor>? {
        return list?.map {
            convertKnownForDtoToEntity(it)
        }
    }

    private fun convertCastMoviesDtoToEntity(castMovieDto: CastMovieDto): CastMovie {
        return CastMovie(
            id = castMovieDto.id,
            title = castMovieDto.title,
            year = castMovieDto.year,
            role = castMovieDto.role,
            description = castMovieDto.description
        )
    }

    private fun mapListCastMoviesDtoToEntity(list: List<CastMovieDto>?): List<CastMovie>? {
        return list?.map {
            convertCastMoviesDtoToEntity(it)
        }
    }
}