package com.example.moviepagination.data.converters

import androidx.room.TypeConverter
import com.example.moviepagination.data.database.model.GenreDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter  {
    @TypeConverter
    fun mapListToString(value: List<GenreDbModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<GenreDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<GenreDbModel> {
        val gson = Gson()
        val type = object : TypeToken<List<GenreDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}
