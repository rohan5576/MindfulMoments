package com.example.mindfulmoments.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mindfulmoments.data.local.converter.StringListConverter

@Entity(tableName = "breathing_sessions")
@TypeConverters(StringListConverter::class)
data class BreathingSession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Int = 1,
    val title: String,
    val duration: Int, // in minutes
    val pattern: String, // "4-7-8", "box", "triangle", etc.
    val description: String,
    val category: String = "Breathing",
    val difficulty: String = "Beginner",
    val completionCount: Int = 0,
    val rating: Float = 0f, // 0.0 - 5.0
    val tags: List<String> = emptyList(),
    val isPremium: Boolean = false,
    val thumbnailPath: String? = null,
    val instructor: String? = null,
    val language: String = "en",
    val completed: Boolean = true,
    val sessionTimestamp: Long = System.currentTimeMillis()
)
