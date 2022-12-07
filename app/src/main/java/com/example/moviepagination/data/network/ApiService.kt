package com.example.moviepagination.data.network

import com.example.moviepagination.data.network.dto.MovieItemListDto
import com.example.moviepagination.data.network.dto.castinfodb.ActorInfoDto
import com.example.moviepagination.data.network.dto.infodb.MovieInfoDto
import com.example.moviepagination.data.network.dto.infodb.YouTubeTrailerDto
import com.example.moviepagination.data.network.dto.searchdb.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/API/InTheaters/{apiKey}")
    suspend fun getActiveMoviesInTheatres(
        @Path("apiKey") apiKey: String,
    ): MovieItemListDto

    @GET("/{lang}/API/Title/{apiKey}/{id}/Trailer")
    suspend fun getMovieById(
        @Path("lang") lang: String,
        @Path("id") id: String,
        @Path("apiKey") apiKey: String,
    ): MovieInfoDto

    @GET("/{lang}/API/ComingSoon/{apiKey}")
    suspend fun getComingSoonMovies(
        @Path("lang") lang: String,
        @Path("apiKey") apiKey: String,
    ): MovieItemListDto

    @GET("/API/Top250Movies/{apiKey}")
    suspend fun getTOP250Movies(
        @Path("apiKey") apiKey: String,
    ): MovieItemListDto

    @GET("/API/Top250TVs/{apiKey}")
    suspend fun getTOP250TVs(
        @Path("apiKey") apiKey: String,
    ): MovieItemListDto

    @GET("/{lang}/API/MostPopularMovies/{apiKey}")
    suspend fun getMostPopularMovies(
        @Path("lang") lang: String,
        @Path("apiKey") apiKey: String,
    ): MovieItemListDto

    @GET("/{lang}/API/MostPopularTVs/{apiKey}")
    suspend fun getMostPopularTVs(
        @Path("lang") lang: String,
        @Path("apiKey") apiKey: String,
    ): MovieItemListDto

    @GET("/{lang}/API/Name/{apiKey}/{id}")
    suspend fun getActorInfoById(
        @Path("lang") lang: String,
        @Path("apiKey") apiKey: String,
        @Path("id") id: String,
    ): ActorInfoDto

    @GET("/API/Search/{apiKey}/{expression}")
    suspend fun getSearchList(
        @Path("apiKey") apiKey: String,
        @Path("expression") expression: String,
    ): SearchResultDto

    @GET("/API/YouTubeTrailer/{apiKey}/{id}")
    suspend fun getMovieTrailerById(
        @Path("id") id: String,
        @Path("apiKey") apiKey: String,
    ): YouTubeTrailerDto
}