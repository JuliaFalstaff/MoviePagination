package com.example.moviepagination.utils

import androidx.room.TypeConverter
import com.example.moviepagination.data.database.model.ItemDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(segments: List<ItemDbModel?>?): String {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun formJson(json: String?): List<ItemDbModel> {
        return gson.fromJson<List<ItemDbModel>>(
            json,
            object : TypeToken<List<ItemDbModel?>?>() {}.type
        )
    }

}