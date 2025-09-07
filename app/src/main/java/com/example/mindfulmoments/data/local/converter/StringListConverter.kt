package com.example.mindfulmoments.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    
    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value == null) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return try {
            Gson().fromJson(value, listType)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    @TypeConverter
    fun fromList(list: List<String>?): String {
        if (list == null) return "[]"
        return try {
            Gson().toJson(list)
        } catch (e: Exception) {
            "[]"
        }
    }
}
