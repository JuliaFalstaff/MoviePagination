package com.example.moviepagination.model.repository

import com.example.moviepagination.BuildConfig
import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.castInfo.ActorInfo
import com.example.moviepagination.model.data.info.MovieInfo
import com.example.moviepagination.model.data.info.YouTubeTrailer
import com.example.moviepagination.model.data.search.SearchResult
import com.example.moviepagination.model.retrofit.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteRepoImpl(private val apiService: ApiService) : IRemoteRepo {

    override fun getMovieNowInTheatre(): Single<MovieItemList> {
        return apiService.getActiveMoviesInTheatres(MOVIE_API_KEY)
    }

    override fun getMovieByIdFromServer(movieId: String): Single<MovieInfo> {
        return apiService.getMovieById(EN_LANG, movieId, MOVIE_API_KEY)
    }

    override fun getComingSoonMoviesFromServer(): Single<MovieItemList> {
        return apiService.getComingSoonMovies(EN_LANG, MOVIE_API_KEY)
    }

    override fun getTOP250Movies(): Single<MovieItemList> {
        return apiService.getTOP250Movies(MOVIE_API_KEY)
    }

    override fun getMostPopularMovies(): Single<MovieItemList> {
        return apiService.getMostPopularMovies(EN_LANG, MOVIE_API_KEY)
    }

    override fun getMostPopularTVs(): Single<MovieItemList> {
        return apiService.getMostPopularTVs(EN_LANG, MOVIE_API_KEY)
    }

    override fun getActorInfoById(actorId: String): Single<ActorInfo> {
        return apiService.getActorInfoById(EN_LANG, MOVIE_API_KEY, actorId)
    }

    override fun getSearchList(expression: String): Single<SearchResult> {
        return apiService.getSearchList(MOVIE_API_KEY, expression)
    }

    override fun getTOP250TVs(): Single<MovieItemList> {
        return apiService.getTOP250TVs(MOVIE_API_KEY)
    }

    override fun getMovieTrailerById(movieId: String): Single<YouTubeTrailer> {
        return apiService.getMovieTrailerById(movieId, MOVIE_API_KEY)
    }

    companion object {
        private const val MOVIE_API_KEY = BuildConfig.IMDb_API_KEY
        private const val EN_LANG = "en"
        private const val RU_LANG = "ru"
    }
}