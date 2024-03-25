package com.example

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromDouble(value: Double): Long {
        return value.toBits()
    }

    @TypeConverter
    fun toDouble(value: Long): Double {
        return Double.fromBits(value)
    }


    @TypeConverter
    fun listOfStringToString(str: List<String>?): String? {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun strToListString(str: String?): List<String>? {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }
}
