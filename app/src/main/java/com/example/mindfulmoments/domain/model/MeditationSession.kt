package com.example.mindfulmoments.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mindfulmoments.data.local.converter.StringListConverter

@Entity(tableName = "meditation_sessions")
@TypeConverters(StringListConverter::class)
data class MeditationSession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Int = 1, // Default user ID for now
    val title: String,
    val duration: Int, // in minutes
    val category: String,
    val difficulty: String,
    val audioPath: String,
    val description: String,
    val completionCount: Int = 0,
    val rating: Float = 0f, // 0.0 - 5.0
    val tags: List<String> = emptyList(),
    val isPremium: Boolean = false,
    val thumbnailPath: String? = null,
    val instructor: String? = null,
    val language: String = "en",
    val completed: Boolean = true, // Track if session was completed
    val sessionTimestamp: Long = System.currentTimeMillis() // Track when session was done
)
