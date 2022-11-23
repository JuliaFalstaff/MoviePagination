package com.example.moviepagination.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

abstract class BaseConverter<T> {

    @TypeConverter
    fun mapListToString(value: List<T>): String {
        val gson = Gson()
        val type = object : TypeToken<List<T>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<T> {
        val gson = Gson()
        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(value, type)
    }
}