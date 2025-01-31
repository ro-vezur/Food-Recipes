package com.example.foodrecipes.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RoomTypeConverters {

    @TypeConverter
    fun convertStringListToJson(list: List<String>): String = Json.encodeToString(list)
    @TypeConverter
    fun convertJsonToStringList(json: String): List<String> = Json.decodeFromString(json)

}