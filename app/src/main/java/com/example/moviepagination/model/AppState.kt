package com.example.moviepagination.model

import com.example.moviepagination.model.data.Item
import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.castInfo.ActorInfo
import com.example.moviepagination.model.data.info.MovieInfo
import com.example.moviepagination.model.data.search.SearchResult

sealed class AppState {
    data class Success(val dataMovie: MovieItemList): AppState()
    data class SuccessMovieInfo(val dataMovie: MovieInfo): AppState()
    data class SuccessActorInfo(val actorInfo: ActorInfo) : AppState()
    data class SuccessSearchResult(val searchResult: SearchResult) : AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}