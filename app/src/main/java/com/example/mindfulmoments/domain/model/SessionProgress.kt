package com.example.mindfulmoments.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "session_progress",
    foreignKeys = [
        ForeignKey(
            entity = MeditationSession::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["sessionId"])
    ]
)
data class SessionProgress(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sessionId: Long,
    val completedAt: Long,
    val durationCompleted: Int, // in seconds
    val moodBefore: Int, // 1-10 scale
    val moodAfter: Int, // 1-10 scale
    val rating: Int, // 1-5 stars
    val notes: String? = null,
    val interrupted: Boolean = false,
    val completionPercentage: Float = 0f // 0.0 - 1.0
)
