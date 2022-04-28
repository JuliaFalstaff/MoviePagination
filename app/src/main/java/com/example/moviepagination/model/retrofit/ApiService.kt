package com.example.moviepagination.model.retrofit

import com.example.moviepagination.model.data.MovieItemList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("InTheaters/")
    fun getActiveMoviesInTheatres(
        @Query("apiKey") apiKey: String
    ): Single<MovieItemList>
}