package com.example.mindfulmoments.data.repository

import com.example.mindfulmoments.data.local.ProgressDao
import com.example.mindfulmoments.data.local.UserProfileDao
import com.example.mindfulmoments.domain.model.*
import com.example.mindfulmoments.data.local.OverallProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgressRepository @Inject constructor(
    private val progressDao: ProgressDao,
    private val userProfileDao: UserProfileDao
) {
    
    // Get current user profile
    fun getCurrentUserProfile(): Flow<UserProfile?> {
        return userProfileDao.getCurrentUserProfile()
    }
    
    // Save user profile
    suspend fun saveUserProfile(name: String, age: String?, goal: String): Long {
        val userProfile = UserProfile(
            name = name,
            age = age,
            goal = goal
        )
        val userId = userProfileDao.insertUserProfile(userProfile)
        
        // Initialize progress data for the new user
        val progressData = ProgressData(
            userId = userId.toInt(),
            weeklyGoal = 5,
            weeklyProgress = 0,
            currentStreak = 0,
            totalDays = 0
        )
        progressDao.insertProgressData(progressData)
        
        return userId
    }
    
    // Get real progress data for dashboard
    suspend fun getDashboardProgress(userId: Int): DashboardProgress {
        val now = System.currentTimeMillis()
        val weekAgo = now - (7 * 24 * 60 * 60 * 1000) // 7 days ago
        
        // Get overall progress
        val overallProgress = progressDao.getOverallProgress() ?: OverallProgress(0, 0, 0)
        
        // Get current streak
        val currentStreak = progressDao.getCurrentStreak() ?: 0
        
        // Get weekly progress
        val weeklyProgress = progressDao.getWeeklyProgress() ?: 0
        
        // Get mood progress (average of last 7 days)
        val moodEntries = progressDao.getMoodEntriesFromDate(userId, weekAgo).first()
        val moodProgress = if (moodEntries.isNotEmpty()) {
            val totalScore = moodEntries.sumOf { it.intensity }
            (totalScore / moodEntries.size) * 10 // Convert to percentage
        } else {
            75 // Default mood if no entries
        }
        
        // Get meditation progress (based on weekly goal)
        val weeklyGoal = 5 // Default weekly goal
        val meditationProgress = if (weeklyGoal > 0) {
            (weeklyProgress.toFloat() / weeklyGoal.toFloat() * 100).toInt()
        } else {
            0
        }
        
        // Get breathing progress (based on weekly goal)
        val breathingProgress = if (weeklyGoal > 0) {
            (weeklyProgress.toFloat() / weeklyGoal.toFloat() * 100).toInt()
        } else {
            0
        }
        
        return DashboardProgress(
            moodProgress = moodProgress.coerceIn(0, 100),
            meditationProgress = meditationProgress.coerceIn(0, 100),
            breathingProgress = breathingProgress.coerceIn(0, 100),
            totalSessions = overallProgress.totalSessions,
            totalMinutes = overallProgress.totalMinutes,
            currentStreak = currentStreak,
            weeklyGoal = weeklyGoal,
            weeklyProgress = weeklyProgress,
            totalDays = overallProgress.totalDays
        )
    }
    
    // Add mood entry
    suspend fun addMoodEntry(userId: Int, moodType: String, intensity: Int, notes: String?) {
        val moodEntry = MoodEntry(
            userId = userId,
            mood = moodType,
            intensity = intensity,
            notes = notes ?: "",
            timestamp = System.currentTimeMillis(),
            tags = emptyList()
        )
        progressDao.insertMoodEntry(moodEntry)
        
        // Update progress data
        updateProgressData(userId)
    }
    
    // Add meditation session
    suspend fun addMeditationSession(userId: Int, duration: Int, type: String) {
        val meditationSession = MeditationSession(
            userId = userId,
            title = type,
            duration = duration,
            category = "Meditation",
            difficulty = "Beginner",
            audioPath = "",
            description = "Meditation session",
            completed = true,
            sessionTimestamp = System.currentTimeMillis()
        )
        progressDao.insertMeditationSession(meditationSession)
        
        // Update progress data
        updateProgressData(userId)
    }
    
    // Add breathing session
    suspend fun addBreathingSession(userId: Int, duration: Int, pattern: String) {
        val breathingSession = BreathingSession(
            userId = userId,
            title = pattern,
            duration = duration,
            pattern = pattern,
            description = "Breathing exercise",
            completed = true,
            sessionTimestamp = System.currentTimeMillis()
        )
        progressDao.insertBreathingSession(breathingSession)
        
        // Update progress data
        updateProgressData(userId)
    }
    
    // Update progress data
    private suspend fun updateProgressData(userId: Int) {
        val now = System.currentTimeMillis()
        val weekAgo = now - (7 * 24 * 60 * 60 * 1000)
        
        val weeklyProgress = progressDao.getWeeklyProgress() ?: 0
        val currentStreak = progressDao.getCurrentStreak() ?: 0
        val overallProgress = progressDao.getOverallProgress() ?: OverallProgress(0, 0, 0)
        
        val progressData = ProgressData(
            userId = userId,
            weeklyProgress = weeklyProgress,
            currentStreak = currentStreak,
            totalSessions = overallProgress.totalSessions,
            totalDays = overallProgress.totalDays,
            lastUpdated = now
        )
        
        progressDao.insertProgressData(progressData)
    }
    
    // Get mood history
    fun getMoodHistory(userId: Int): Flow<List<MoodEntry>> {
        return progressDao.getAllMoodEntries(userId)
    }
    
    // Get meditation history
    fun getMeditationHistory(userId: Int): Flow<List<MeditationSession>> {
        return progressDao.getAllMeditationSessions(userId)
    }
    
    // Get breathing history
    fun getBreathingHistory(userId: Int): Flow<List<BreathingSession>> {
        return progressDao.getAllBreathingSessions(userId)
    }
}

data class DashboardProgress(
    val moodProgress: Int,
    val meditationProgress: Int,
    val breathingProgress: Int,
    val totalSessions: Int,
    val totalMinutes: Int,
    val currentStreak: Int,
    val weeklyGoal: Int,
    val weeklyProgress: Int,
    val totalDays: Int
)
