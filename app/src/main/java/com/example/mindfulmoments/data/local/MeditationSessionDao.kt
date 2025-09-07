package com.example.mindfulmoments.data.local

import androidx.room.*
import com.example.mindfulmoments.domain.model.MeditationSession
import kotlinx.coroutines.flow.Flow

@Dao
interface MeditationSessionDao {
    
    @Query("SELECT * FROM meditation_sessions ORDER BY title ASC")
    fun getAllSessions(): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions WHERE category = :category ORDER BY title ASC")
    fun getSessionsByCategory(category: String): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions WHERE difficulty = :difficulty ORDER BY title ASC")
    fun getSessionsByDifficulty(difficulty: String): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions WHERE duration <= :maxDuration ORDER BY duration ASC")
    fun getSessionsByMaxDuration(maxDuration: Int): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions WHERE id = :id")
    suspend fun getSessionById(id: Long): MeditationSession?
    
    @Query("SELECT * FROM meditation_sessions WHERE tags LIKE '%' || :tag || '%' ORDER BY title ASC")
    fun getSessionsByTag(tag: String): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions WHERE isPremium = :isPremium ORDER BY title ASC")
    fun getSessionsByPremiumStatus(isPremium: Boolean): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions ORDER BY rating DESC LIMIT :limit")
    fun getTopRatedSessions(limit: Int): Flow<List<MeditationSession>>
    
    @Query("SELECT * FROM meditation_sessions ORDER BY completionCount DESC LIMIT :limit")
    fun getMostCompletedSessions(limit: Int): Flow<List<MeditationSession>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: MeditationSession): Long
    
    @Update
    suspend fun updateSession(session: MeditationSession)
    
    @Delete
    suspend fun deleteSession(session: MeditationSession)
    
    @Query("UPDATE meditation_sessions SET completionCount = completionCount + 1 WHERE id = :sessionId")
    suspend fun incrementCompletionCount(sessionId: Long)
    
    @Query("UPDATE meditation_sessions SET rating = :rating WHERE id = :sessionId")
    suspend fun updateSessionRating(sessionId: Long, rating: Float)
    
    @Query("SELECT COUNT(*) FROM meditation_sessions")
    suspend fun getSessionCount(): Int
    
    @Query("SELECT DISTINCT category FROM meditation_sessions ORDER BY category ASC")
    fun getAllCategories(): Flow<List<String>>
    
    @Query("SELECT DISTINCT difficulty FROM meditation_sessions ORDER BY difficulty ASC")
        fun getAllDifficulties(): Flow<List<String>>
}
