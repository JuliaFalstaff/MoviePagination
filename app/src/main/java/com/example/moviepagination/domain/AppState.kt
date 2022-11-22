package com.example.moviepagination.domain

import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult

sealed class AppState {
    data class Success(val dataMovie: MovieItemList): AppState()
    data class SuccessMovieInfo(val dataMovie: MovieInfo): AppState()
    data class SuccessTrailer(val trailerMovie: YouTubeTrailer): AppState()
    data class SuccessActorInfo(val actorInfo: ActorInfo) : AppState()
    data class SuccessSearchResult(val searchResult: SearchResult) : AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}