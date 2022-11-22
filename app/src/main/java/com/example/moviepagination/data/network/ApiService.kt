package com.example.moviepagination.data.network

import com.example.moviepagination.data.network.model.MovieItemListDto
import com.example.moviepagination.data.network.model.castinfodb.ActorInfoDto
import com.example.moviepagination.data.network.model.infodb.MovieInfoDto
import com.example.moviepagination.data.network.model.infodb.YouTubeTrailerDto
import com.example.moviepagination.data.network.model.searchdb.SearchResultDto
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/API/InTheaters/{apiKey}")
    fun getActiveMoviesInTheatres(
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemListDto>

    @GET("/{lang}/API/Title/{apiKey}/{id}/Trailer")
    fun getMovieById(
            @Path("lang") lang: String,
            @Path("id") id: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieInfoDto>

    @GET("/{lang}/API/ComingSoon/{apiKey}")
    fun getComingSoonMovies(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemListDto>

    @GET("/API/Top250Movies/{apiKey}")
    fun getTOP250Movies(
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemListDto>

    @GET("/API/Top250TVs/{apiKey}")
    fun getTOP250TVs(
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemListDto>

    @GET("/{lang}/API/MostPopularMovies/{apiKey}")
    fun getMostPopularMovies(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemListDto>

    @GET("/{lang}/API/MostPopularTVs/{apiKey}")
    fun getMostPopularTVs(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
    ): Single<MovieItemListDto>

    @GET("/{lang}/API/Name/{apiKey}/{id}")
    fun getActorInfoById(
            @Path("lang") lang: String,
            @Path("apiKey") apiKey: String,
            @Path("id") id: String,
    ): Single<ActorInfoDto>

    @GET("/API/Search/{apiKey}/{expression}")
    fun getSearchList(
            @Path("apiKey") apiKey: String,
            @Path("expression") expression: String,
    ): Single<SearchResultDto>

    @GET("/API/YouTubeTrailer/{apiKey}/{id}")
    fun getMovieTrailerById(
        @Path("id") id: String,
        @Path("apiKey") apiKey: String,
    ): Single<YouTubeTrailerDto>
}