package com.example.moviepagination.utils

import androidx.room.TypeConverter
import com.example.moviepagination.model.room.entities.ItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()
    @TypeConverter
    fun toJson(segments: List<ItemEntity?>?): String {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun formJson(json: String?): List<ItemEntity> {
        return gson.fromJson<List<ItemEntity>>(
            json,
            object : TypeToken<List<ItemEntity?>?>() {}.type
        )
    }
}