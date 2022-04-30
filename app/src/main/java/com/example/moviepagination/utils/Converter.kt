package com.example.moviepagination.utils

import androidx.room.TypeConverter
import com.example.moviepagination.model.data.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(segments: List<Item?>?): String {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun formJson(json: String?): List<Item> {
        return gson.fromJson<List<Item>>(
                json,
                object : TypeToken<List<Item?>?>() {}.type
        )
    }
}