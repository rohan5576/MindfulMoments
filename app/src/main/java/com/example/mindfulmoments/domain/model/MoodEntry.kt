package com.example.mindfulmoments.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mindfulmoments.data.local.converter.StringListConverter

@Entity(tableName = "mood_entries")
@TypeConverters(StringListConverter::class)
data class MoodEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Int = 1, // Default user ID for now
    val mood: String,
    val intensity: Int, // 1-10 scale
    val notes: String,
    val timestamp: Long,
    val tags: List<String>,
    val location: String? = null,
    val weather: String? = null,
    val activity: String? = null,
    val sleepHours: Float? = null,
    val stressLevel: Int? = null // 1-10 scale
)
