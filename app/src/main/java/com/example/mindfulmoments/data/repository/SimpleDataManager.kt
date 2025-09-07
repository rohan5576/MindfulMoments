package com.example.mindfulmoments.data.repository

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class SimpleDataManager(private val context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("mindful_moments_data", Context.MODE_PRIVATE)
    
    // User Profile
    fun saveUserProfile(name: String, age: String, goal: String): Long {
        val userId = System.currentTimeMillis()
        prefs.edit()
            .putLong("user_id", userId)
            .putString("user_name", name)
            .putString("user_age", age)
            .putString("user_goal", goal)
            .putLong("profile_created", System.currentTimeMillis())
            .apply()
        return userId
    }
    
    fun getUserProfile(): UserProfileData? {
        val name = prefs.getString("user_name", null)
        val age = prefs.getString("user_age", null)
        val goal = prefs.getString("user_goal", null)
        
        return if (name != null && goal != null) {
            UserProfileData(
                name = name,
                age = age,
                goal = goal
            )
        } else null
    }
    
    // Mood Entries
    fun addMoodEntry(moodType: String, intensity: Int, notes: String?) {
        val entries = getMoodEntries().toMutableList()
        val newEntry = MoodEntryData(
            id = System.currentTimeMillis(),
            mood = moodType,
            intensity = intensity,
            notes = notes ?: "",
            timestamp = System.currentTimeMillis()
        )
        entries.add(newEntry)
        saveMoodEntries(entries)
        
        // Update progress
        updateProgress()
    }
    
    fun getMoodEntries(): List<MoodEntryData> {
        val entriesJson = prefs.getString("mood_entries", "[]")
        // Simple parsing - in production use proper JSON library
        return if (entriesJson == "[]") emptyList() else {
            // For now, return some sample data
            listOf(
                MoodEntryData(1, "Happy", 8, "Feeling great today", System.currentTimeMillis() - 86400000),
                MoodEntryData(2, "Calm", 7, "Peaceful morning", System.currentTimeMillis() - 172800000),
                MoodEntryData(3, "Focused", 6, "Productive day", System.currentTimeMillis() - 259200000)
            )
        }
    }
    
    private fun saveMoodEntries(entries: List<MoodEntryData>) {
        // In production, save to proper storage
        // For now, just update progress
        updateProgress()
    }
    
    // Meditation Sessions
    fun addMeditationSession(duration: Int, type: String) {
        val sessions = getMeditationSessions().toMutableList()
        val newSession = MeditationSessionData(
            id = System.currentTimeMillis(),
            title = type,
            duration = duration,
            timestamp = System.currentTimeMillis()
        )
        sessions.add(newSession)
        saveMeditationSessions(sessions)
        
        // Update progress
        updateProgress()
    }
    
    fun getMeditationSessions(): List<MeditationSessionData> {
        val sessionsJson = prefs.getString("meditation_sessions", "[]")
        return if (sessionsJson == "[]") emptyList() else {
            // For now, return some sample data
            listOf(
                MeditationSessionData(1, "Mindfulness", 15, System.currentTimeMillis() - 86400000),
                MeditationSessionData(2, "Breathing", 10, System.currentTimeMillis() - 172800000),
                MeditationSessionData(3, "Body Scan", 20, System.currentTimeMillis() - 259200000)
            )
        }
    }
    
    private fun saveMeditationSessions(sessions: List<MeditationSessionData>) {
        // In production, save to proper storage
        // For now, just update progress
        updateProgress()
    }
    
    // Breathing Sessions
    fun addBreathingSession(duration: Int, pattern: String) {
        val sessions = getBreathingSessions().toMutableList()
        val newSession = BreathingSessionData(
            id = System.currentTimeMillis(),
            title = pattern,
            duration = duration,
            timestamp = System.currentTimeMillis()
        )
        sessions.add(newSession)
        saveBreathingSessions(sessions)
        
        // Update progress
        updateProgress()
    }
    
    fun getBreathingSessions(): List<BreathingSessionData> {
        val sessionsJson = prefs.getString("breathing_sessions", "[]")
        return if (sessionsJson == "[]") emptyList() else {
            // For now, return some sample data
            listOf(
                BreathingSessionData(1, "4-7-8", 5, System.currentTimeMillis() - 86400000),
                BreathingSessionData(2, "Box", 8, System.currentTimeMillis() - 172800000),
                BreathingSessionData(3, "Triangle", 6, System.currentTimeMillis() - 259200000)
            )
        }
    }
    
    private fun saveBreathingSessions(sessions: List<BreathingSessionData>) {
        // In production, save to proper storage
        // For now, just update progress
        updateProgress()
    }
    
    // Progress Calculation
    private fun updateProgress() {
        val moodEntries = getMoodEntries()
        val meditationSessions = getMeditationSessions()
        val breathingSessions = getBreathingSessions()
        
        // Calculate real progress
        val moodProgress = if (moodEntries.isNotEmpty()) {
            val recentEntries = moodEntries.filter { it.timestamp > System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000 }
            if (recentEntries.isNotEmpty()) {
                (recentEntries.sumOf { it.intensity } / recentEntries.size) * 10
            } else 75
        } else 75
        
        val totalSessions = meditationSessions.size + breathingSessions.size
        val totalMinutes = meditationSessions.sumOf { it.duration } + breathingSessions.sumOf { it.duration }
        
        // Calculate streak
        val allSessions = (meditationSessions + breathingSessions).sortedByDescending { it.timestamp }
        val currentStreak = calculateStreak(allSessions)
        
        // Calculate weekly progress
        val weekAgo = System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000
        val weeklyProgress = allSessions.count { it.timestamp > weekAgo }
        
        // Save progress
        prefs.edit()
            .putInt("mood_progress", moodProgress)
            .putInt("total_sessions", totalSessions)
            .putInt("total_minutes", totalMinutes)
            .putInt("current_streak", currentStreak)
            .putInt("weekly_progress", weeklyProgress)
            .putLong("last_progress_update", System.currentTimeMillis())
            .apply()
    }
    
    private fun calculateStreak(sessions: List<SessionData>): Int {
        if (sessions.isEmpty()) return 0
        
        var streak = 0
        var currentDate = Calendar.getInstance()
        currentDate.timeInMillis = System.currentTimeMillis()
        
        for (i in 0 until 30) { // Check last 30 days
            val dayStart = currentDate.timeInMillis
            val dayEnd = dayStart + 24 * 60 * 60 * 1000
            
            val hasSession = sessions.any { it.timestamp in dayStart until dayEnd }
            
            if (hasSession) {
                streak++
            } else {
                break
            }
            
            currentDate.add(Calendar.DAY_OF_YEAR, -1)
        }
        
        return streak
    }
    
    fun getDashboardProgress(): DashboardProgress {
        val moodProgress = prefs.getInt("mood_progress", 75)
        val totalSessions = prefs.getInt("total_sessions", 0)
        val totalMinutes = prefs.getInt("total_minutes", 0)
        val currentStreak = prefs.getInt("current_streak", 0)
        val weeklyProgress = prefs.getInt("weekly_progress", 0)
        
        // Calculate meditation and breathing progress based on weekly goal
        val weeklyGoal = 5
        val meditationProgress = if (weeklyGoal > 0) {
            (weeklyProgress.toFloat() / weeklyGoal.toFloat() * 100).toInt()
        } else 0
        
        val breathingProgress = meditationProgress // Same calculation for now
        
        return DashboardProgress(
            moodProgress = moodProgress,
            meditationProgress = meditationProgress,
            breathingProgress = breathingProgress,
            totalSessions = totalSessions,
            totalMinutes = totalMinutes,
            currentStreak = currentStreak,
            weeklyGoal = weeklyGoal,
            weeklyProgress = weeklyProgress,
            totalDays = if (totalSessions > 0) 7 else 0 // Simplified calculation
        )
    }
}

// Data classes for simple data management
data class UserProfileData(
    val name: String,
    val age: String?,
    val goal: String
)

data class MoodEntryData(
    val id: Long,
    val mood: String,
    val intensity: Int,
    val notes: String,
    override val timestamp: Long
) : SessionData

data class MeditationSessionData(
    val id: Long,
    val title: String,
    val duration: Int,
    override val timestamp: Long
) : SessionData

data class BreathingSessionData(
    val id: Long,
    val title: String,
    val duration: Int,
    override val timestamp: Long
) : SessionData

interface SessionData {
    val timestamp: Long
}
