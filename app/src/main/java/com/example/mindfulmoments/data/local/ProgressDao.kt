package com.example.mindfulmoments.data.local

import androidx.room.*
import com.example.mindfulmoments.domain.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    
    // Progress Data Operations
    @Query("SELECT * FROM progress_data WHERE userId = :userId ORDER BY date DESC LIMIT 1")
    fun getLatestProgressData(userId: Int): Flow<ProgressData?>
    
    @Query("SELECT * FROM progress_data WHERE userId = :userId ORDER BY date DESC")
    fun getAllProgressData(userId: Int): Flow<List<ProgressData>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgressData(progressData: ProgressData): Long
    
    @Update
    suspend fun updateProgressData(progressData: ProgressData)
    
    // Mood Entries Operations
    @Query("SELECT * FROM mood_entries WHERE userId = :userId ORDER BY timestamp DESC")
    fun getAllMoodEntries(userId: Int): Flow<List<MoodEntry>>
    
    @Query("SELECT * FROM mood_entries WHERE userId = :userId AND timestamp >= :startTime ORDER BY timestamp DESC")
    fun getMoodEntriesFromDate(userId: Int, startTime: Long): Flow<List<MoodEntry>>
    
    @Insert
    suspend fun insertMoodEntry(moodEntry: MoodEntry): Long
    
    // Meditation Sessions Operations
    @Query("SELECT * FROM meditation_sessions WHERE userId = :userId ORDER BY sessionTimestamp DESC")
    fun getAllMeditationSessions(userId: Int): Flow<List<MeditationSession>>
    
    @Insert
    suspend fun insertMeditationSession(meditationSession: MeditationSession): Long
    
    // Breathing Sessions Operations
    @Query("SELECT * FROM breathing_sessions WHERE userId = :userId ORDER BY sessionTimestamp DESC")
    fun getAllBreathingSessions(userId: Int): Flow<List<BreathingSession>>
    
    @Insert
    suspend fun insertBreathingSession(breathingSession: BreathingSession): Long
    
    // Analytics and Calculations
    @Query("""
        SELECT 
            COUNT(*) as totalSessions,
            SUM(duration) as totalMinutes,
            COUNT(DISTINCT DATE(sessionTimestamp/1000, 'unixepoch')) as totalDays
        FROM (
            SELECT sessionTimestamp, duration FROM meditation_sessions 
            WHERE completed = 1
            UNION ALL
            SELECT sessionTimestamp, duration FROM breathing_sessions 
            WHERE completed = 1
        )
    """)
    suspend fun getOverallProgress(): OverallProgress?
    
    @Query("""
        SELECT COUNT(*) as streak
        FROM (
            SELECT DISTINCT DATE(sessionTimestamp/1000, 'unixepoch') as session_date
            FROM (
                SELECT sessionTimestamp FROM meditation_sessions 
                WHERE completed = 1
                UNION ALL
                SELECT sessionTimestamp FROM breathing_sessions 
                WHERE completed = 1
            )
            ORDER BY session_date DESC
        ) dates
        WHERE session_date >= DATE('now', '-30 days')
    """)
    suspend fun getCurrentStreak(): Int?
    
    @Query("""
        SELECT COUNT(*) as weeklyProgress
        FROM (
            SELECT DISTINCT DATE(sessionTimestamp/1000, 'unixepoch') as session_date
            FROM (
                SELECT sessionTimestamp FROM meditation_sessions 
                WHERE completed = 1
                UNION ALL
                SELECT sessionTimestamp FROM breathing_sessions 
                WHERE completed = 1
            )
            WHERE session_date >= DATE('now', '-7 days')
        )
    """)
    suspend fun getWeeklyProgress(): Int?
}

data class OverallProgress(
    val totalSessions: Int,
    val totalMinutes: Int,
    val totalDays: Int
)
