package com.example.moviepagination.utils

import androidx.room.TypeConverter
import com.example.moviepagination.domain.entities.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun<T> toJson(segments: List<T>?): String {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun<T> formJson(json: String?): List<T> {
        return gson.fromJson<List<T>>(
                json,
                object : TypeToken<List<T?>?>() {}.type
        )
    }
}