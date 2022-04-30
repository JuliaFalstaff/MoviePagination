package com.example.moviepagination.model.repository

import com.example.moviepagination.BuildConfig
import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.info.MovieInfo
import com.example.moviepagination.model.retrofit.ApiService
import io.reactivex.rxjava3.core.Single

class RemoteRepoImpl(private val apiService: ApiService) : IRemoteRepo {

    override fun getMovieListFromServer(): Single<MovieItemList> {
        return apiService.getActiveMoviesInTheatres(MOVIE_API_KEY)
    }

    override fun getMovieByIdFromServer(movieId: String): Single<MovieInfo> {
        return apiService.getMovieById(EN_LANG, movieId, MOVIE_API_KEY)
    }

    companion object {
        private const val MOVIE_API_KEY = BuildConfig.IMDb_API_KEY
        private const val EN_LANG = "en"
    }
}