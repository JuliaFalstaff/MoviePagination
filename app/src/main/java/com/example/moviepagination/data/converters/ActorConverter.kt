package com.example.moviepagination.data.converters

import androidx.room.TypeConverter
import com.example.moviepagination.data.database.model.ActorDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ActorConverter {
    @TypeConverter
    fun mapListToString(value: List<ActorDbModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ActorDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<ActorDbModel> {
        val gson = Gson()
        val type = object : TypeToken<List<ActorDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}
