package com.example.mindfulmoments.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress_data")
data class ProgressData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val date: Long = System.currentTimeMillis(),
    val moodScore: Int? = null,
    val meditationMinutes: Int = 0,
    val breathingSessions: Int = 0,
    val totalSessions: Int = 0,
    val weeklyGoal: Int = 5,
    val weeklyProgress: Int = 0,
    val currentStreak: Int = 0,
    val totalDays: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis()
)
