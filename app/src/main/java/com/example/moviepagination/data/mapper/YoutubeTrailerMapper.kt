package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.network.dto.infodb.YouTubeTrailerDto
import com.example.moviepagination.domain.entities.info.YouTubeTrailer

class YoutubeTrailerMapper {
    fun mapYoutubeTrailerDtoToEntity(trailerDto: YouTubeTrailerDto): YouTubeTrailer {
        return YouTubeTrailer(
            imDbId = trailerDto.imDbId,
            title = trailerDto.title,
            fullTitle = trailerDto.fullTitle,
            type = trailerDto.type,
            year = trailerDto.year,
            videoId = trailerDto.videoId ?: EMPTY_STRING_ID,
            errorMessage = trailerDto.errorMessage,
            videoUrl = trailerDto.videoUrl
        )
    }

    companion object {
        private const val EMPTY_STRING_ID = ""
    }
}