package com.example.mindfulmoments.data.local

import androidx.room.*
import com.example.mindfulmoments.domain.model.BreathingSession
import kotlinx.coroutines.flow.Flow

@Dao
interface BreathingSessionDao {
    
    @Query("SELECT * FROM breathing_sessions ORDER BY title ASC")
    fun getAllSessions(): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions WHERE category = :category ORDER BY title ASC")
    fun getSessionsByCategory(category: String): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions WHERE difficulty = :difficulty ORDER BY title ASC")
    fun getSessionsByDifficulty(difficulty: String): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions WHERE duration <= :maxDuration ORDER BY duration ASC")
    fun getSessionsByMaxDuration(maxDuration: Int): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions WHERE id = :id")
    suspend fun getSessionById(id: Long): BreathingSession?
    
    @Query("SELECT * FROM breathing_sessions WHERE tags LIKE '%' || :tag || '%' ORDER BY title ASC")
    fun getSessionsByTag(tag: String): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions WHERE isPremium = :isPremium ORDER BY title ASC")
    fun getSessionsByPremiumStatus(isPremium: Boolean): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions ORDER BY rating DESC LIMIT :limit")
    fun getTopRatedSessions(limit: Int): Flow<List<BreathingSession>>
    
    @Query("SELECT * FROM breathing_sessions ORDER BY completionCount DESC LIMIT :limit")
    fun getMostCompletedSessions(limit: Int): Flow<List<BreathingSession>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: BreathingSession): Long
    
    @Update
    suspend fun updateSession(session: BreathingSession)
    
    @Delete
    suspend fun deleteSession(session: BreathingSession)
    
    @Query("UPDATE breathing_sessions SET completionCount = completionCount + 1 WHERE id = :sessionId")
    suspend fun incrementCompletionCount(sessionId: Long)
    
    @Query("UPDATE breathing_sessions SET rating = :rating WHERE id = :sessionId")
    suspend fun updateSessionRating(sessionId: Long, rating: Float)
    
    @Query("SELECT COUNT(*) FROM breathing_sessions")
    suspend fun getSessionCount(): Int
    
    @Query("SELECT DISTINCT category FROM breathing_sessions ORDER BY category ASC")
    fun getAllCategories(): Flow<List<String>>
    
    @Query("SELECT DISTINCT difficulty FROM breathing_sessions ORDER BY difficulty ASC")
    fun getAllDifficulties(): Flow<List<String>>
    
    // Progress tracking queries
    @Query("SELECT * FROM breathing_sessions WHERE userId = :userId AND completed = 1 ORDER BY sessionTimestamp DESC")
    fun getCompletedSessionsByUser(userId: Int): Flow<List<BreathingSession>>
    
    @Query("SELECT SUM(duration) FROM breathing_sessions WHERE userId = :userId AND completed = 1")
    suspend fun getTotalMinutesByUser(userId: Int): Int?
    
    @Query("SELECT COUNT(*) FROM breathing_sessions WHERE userId = :userId AND completed = 1")
    suspend fun getTotalSessionsByUser(userId: Int): Int?
}
