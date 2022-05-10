package com.example.moviepagination.model.retrofit

import com.example.moviepagination.model.data.MovieItemList
import com.example.moviepagination.model.data.castInfo.ActorInfo
import com.example.moviepagination.model.data.info.MovieInfo
import com.example.moviepagination.model.data.search.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/API/InTheaters/{apiKey}")
    fun getActiveMoviesInTheatres(
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemList>

    @GET("/{lang}/API/Title/{apiKey}/{id}")
    fun getMovieById(
            @Path("lang") lang: String,
            @Path("id") id: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieInfo>

    @GET("/{lang}/API/ComingSoon/{apiKey}")
    fun getComingSoonMovies(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemList>

    @GET("/API/Top250Movies/{apiKey}")
    fun getTOP250Movies(
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemList>

    @GET("/API/Top250TVs/{apiKey}")
    fun getTOP250TVs(
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemList>

    @GET("/{lang}/API/MostPopularMovies/{apiKey}")
    fun getMostPopularMovies(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemList>

    @GET("/{lang}/API/MostPopularTVs/{apiKey}")
    fun getMostPopularTVs(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemList>

    @GET("/{lang}/API/Name/{apiKey}/{id}")
    fun getActorInfoById(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
            @Path("id") id: String,
    ): Single<ActorInfo>

    @GET("/API/Search/{apiKey}/{expression}")
    fun getSearchList(
            @Path("apiKey") apiKey: String,
            @Path("expression") expression: String,
    ): Single<SearchResult>
}