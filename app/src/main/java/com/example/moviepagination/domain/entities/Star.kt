package com.example.moviepagination.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Star(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
) : Parcelable
