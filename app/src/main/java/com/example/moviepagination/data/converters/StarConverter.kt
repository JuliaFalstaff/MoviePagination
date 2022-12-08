package com.example.moviepagination.data.converters

import androidx.room.TypeConverter
import com.example.moviepagination.data.database.model.GenreDbModel
import com.example.moviepagination.data.database.model.StarDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StarConverter  {
    @TypeConverter
    fun mapListToString(value: List<StarDbModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<StarDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<StarDbModel> {
        val gson = Gson()
        val type = object : TypeToken<List<StarDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}
