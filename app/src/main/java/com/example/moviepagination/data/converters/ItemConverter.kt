package com.example.moviepagination.data.converters

import androidx.room.TypeConverter
import com.example.moviepagination.data.database.model.ItemDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemConverter {
    @TypeConverter
    fun mapListToString(value: List<ItemDbModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ItemDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<ItemDbModel> {
        val gson = Gson()
        val type = object : TypeToken<List<ItemDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}
