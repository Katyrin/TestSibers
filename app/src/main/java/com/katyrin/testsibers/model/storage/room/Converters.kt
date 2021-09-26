package com.katyrin.testsibers.model.storage.room

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken


object Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> =
        Gson().fromJson(value, object : TypeToken<List<String?>?>() {}.type)

    @TypeConverter
    fun fromList(list: List<String?>?): String = Gson().toJson(list)
}