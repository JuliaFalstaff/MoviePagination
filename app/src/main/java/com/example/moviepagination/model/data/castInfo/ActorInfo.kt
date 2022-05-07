package com.example.moviepagination.model.data.castInfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActorInfo(
        @SerializedName("id")
        val id: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("role")
        val role: String = "",
        @SerializedName("image")
        val image: String = "",
        @SerializedName("summary")
        val summary: String = "",
        @SerializedName("birthDate")
        val birthDate: String = "",
        @SerializedName("deathDate")
        val deathDate: String = "",
        @SerializedName("awards")
        val awards: String = "",
        @SerializedName("height")
        val height: String = "",
        @SerializedName("knownFor")
        val knownFor: List<KnownFor>? = null,
        @SerializedName("castMovies")
        val castMovies: List<CastMovie>? = null,
        @SerializedName("errorMessage")
        val errorMessage: String = ""
        ) : Parcelable
