package com.example.moviepagination.model.retrofit

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.info.MovieInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/API/InTheaters/{apiKey}")
    fun getActiveMoviesInTheatres(
        @Path("apiKey") apiKey: String
    ): Single<MovieItemList>

    @GET("/{lang}/API/Title/{apiKey}/{id}")
    fun getMovieById(
        @Path("lang") lang: String,
        @Path("id") id: String,
        @Path("apiKey") apiKey: String
    ): Single<MovieInfo>

    @GET("/{lang}/API/ComingSoon/{apiKey}")
    fun getComingSoonMovies(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String
    ): Single<MovieItemList>
}