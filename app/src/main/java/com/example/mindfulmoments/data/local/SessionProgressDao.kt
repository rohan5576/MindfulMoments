package com.example.mindfulmoments.data.local

import androidx.room.*
import com.example.mindfulmoments.domain.model.SessionProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionProgressDao {
    
    @Query("SELECT * FROM session_progress ORDER BY completedAt DESC")
    fun getAllProgress(): Flow<List<SessionProgress>>
    
    @Query("SELECT * FROM session_progress WHERE sessionId = :sessionId ORDER BY completedAt DESC")
    fun getProgressBySessionId(sessionId: Long): Flow<List<SessionProgress>>
    
    @Query("SELECT * FROM session_progress WHERE completedAt >= :startTime AND completedAt <= :endTime ORDER BY completedAt DESC")
    fun getProgressInRange(startTime: Long, endTime: Long): Flow<List<SessionProgress>>
    
    @Query("SELECT * FROM session_progress WHERE id = :id")
    suspend fun getProgressById(id: Long): SessionProgress?
    
    @Query("SELECT * FROM session_progress WHERE sessionId = :sessionId ORDER BY completedAt DESC LIMIT 1")
    suspend fun getLatestProgressForSession(sessionId: Long): SessionProgress?
    
    @Query("SELECT COUNT(*) FROM session_progress WHERE sessionId = :sessionId")
    suspend fun getProgressCountForSession(sessionId: Long): Int
    
    @Query("SELECT AVG(moodAfter - moodBefore) FROM session_progress WHERE sessionId = :sessionId")
    suspend fun getAverageMoodImprovementForSession(sessionId: Long): Float?
    
    @Query("SELECT AVG(rating) FROM session_progress WHERE sessionId = :sessionId")
    suspend fun getAverageRatingForSession(sessionId: Long): Float?
    
    @Query("SELECT SUM(durationCompleted) FROM session_progress WHERE sessionId = :sessionId")
    suspend fun getTotalTimeSpentOnSession(sessionId: Long): Int?
    
    @Query("SELECT * FROM session_progress WHERE completedAt >= :startTime ORDER BY completedAt DESC LIMIT :limit")
    fun getRecentProgress(startTime: Long, limit: Int): Flow<List<SessionProgress>>
    
    @Query("SELECT COUNT(*) FROM session_progress WHERE completedAt >= :startTime")
    suspend fun getProgressCountInRange(startTime: Long): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: SessionProgress): Long
    
    @Update
    suspend fun updateProgress(progress: SessionProgress)
    
    @Delete
    suspend fun deleteProgress(progress: SessionProgress)
    
    @Query("DELETE FROM session_progress WHERE id = :id")
    suspend fun deleteProgressById(id: Long)
    
    @Query("SELECT COUNT(*) FROM session_progress")
    suspend fun getTotalProgressCount(): Int
}
