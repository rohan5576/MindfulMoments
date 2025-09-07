package com.example.mindfulmoments.data.local

import androidx.room.*
import com.example.mindfulmoments.domain.model.MoodEntry
import kotlinx.coroutines.flow.Flow

data class MoodDistribution(
    val mood: String,
    val count: Int
)

@Dao
interface MoodEntryDao {
    
    @Query("SELECT * FROM mood_entries ORDER BY timestamp DESC")
    fun getAllMoodEntries(): Flow<List<MoodEntry>>
    
    @Query("SELECT * FROM mood_entries WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp DESC")
    fun getMoodEntriesInRange(startTime: Long, endTime: Long): Flow<List<MoodEntry>>
    
    @Query("SELECT * FROM mood_entries WHERE mood = :mood ORDER BY timestamp DESC")
    fun getMoodEntriesByMood(mood: String): Flow<List<MoodEntry>>
    
    @Query("SELECT * FROM mood_entries WHERE id = :id")
    suspend fun getMoodEntryById(id: Long): MoodEntry?
    
    @Query("SELECT * FROM mood_entries ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestMoodEntry(): MoodEntry?
    
    @Query("SELECT AVG(intensity) FROM mood_entries WHERE timestamp >= :startTime AND timestamp <= :endTime")
    suspend fun getAverageMoodIntensity(startTime: Long, endTime: Long): Float?
    
    @Query("SELECT mood, COUNT(*) as count FROM mood_entries GROUP BY mood ORDER BY count DESC")
    fun getMoodDistribution(): Flow<List<MoodDistribution>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoodEntry(moodEntry: MoodEntry): Long
    
    @Update
    suspend fun updateMoodEntry(moodEntry: MoodEntry)
    
    @Delete
    suspend fun deleteMoodEntry(moodEntry: MoodEntry)
    
    @Query("DELETE FROM mood_entries WHERE id = :id")
    suspend fun deleteMoodEntryById(id: Long)
    
    @Query("SELECT COUNT(*) FROM mood_entries")
    suspend fun getMoodEntryCount(): Int
}
