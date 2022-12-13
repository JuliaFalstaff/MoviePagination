package com.example.moviepagination.data.converters

import androidx.room.TypeConverter
import com.example.moviepagination.data.database.model.DirectorDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DirectorConverter {
    @TypeConverter
    fun mapListToString(value: List<DirectorDbModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DirectorDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<DirectorDbModel> {
        val gson = Gson()
        val type = object : TypeToken<List<DirectorDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}
