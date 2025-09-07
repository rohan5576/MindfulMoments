package com.example.mindfulmoments.data.repository

import android.content.Context
import com.example.mindfulmoments.domain.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Comprehensive data manager that provides real-time data across all app screens
 * Integrates SimpleDataManager with additional functionality for cross-screen data sharing
 */
class AppDataManager(context: Context) {
    
    private val simpleDataManager = SimpleDataManager(context)
    
    // Real-time data flows for all screens
    private val _dashboardData = MutableStateFlow(DashboardData())
    val dashboardData: StateFlow<DashboardData> = _dashboardData.asStateFlow()
    
    private val _moodData = MutableStateFlow(MoodData())
    val moodData: StateFlow<MoodData> = _moodData.asStateFlow()
    
    private val _meditationData = MutableStateFlow(MeditationData())
    val meditationData: StateFlow<MeditationData> = _meditationData.asStateFlow()
    
    private val _breathingData = MutableStateFlow(BreathingData())
    val breathingData: StateFlow<BreathingData> = _breathingData.asStateFlow()
    
    private val _insightsData = MutableStateFlow(InsightsData())
    val insightsData: StateFlow<InsightsData> = _insightsData.asStateFlow()
    
    init {
        // Initialize with empty data, refresh will be called when needed
    }
    
    /**
     * Refresh all data across the app
     */
    suspend fun refreshAllData() {
        try {
            // Load user profile
            val userProfile = simpleDataManager.getUserProfile()
            
            // Load all session data
            val moodEntries = simpleDataManager.getMoodEntries()
            val meditationSessions = simpleDataManager.getMeditationSessions()
            val breathingSessions = simpleDataManager.getBreathingSessions()
            
            // Calculate dashboard metrics
            val dashboardProgress = simpleDataManager.getDashboardProgress()
            
            // Update all data flows
            _dashboardData.value = DashboardData(
                userProfile = userProfile,
                progress = DashboardProgress(
                    moodProgress = if (moodEntries.isNotEmpty()) (moodEntries.map { it.intensity }.average() * 10).toInt() else 0,
                    meditationProgress = if (meditationSessions.isNotEmpty()) (meditationSessions.size * 10) else 0,
                    breathingProgress = if (breathingSessions.isNotEmpty()) (breathingSessions.size * 10) else 0,
                    totalSessions = moodEntries.size + meditationSessions.size + breathingSessions.size,
                    totalMinutes = meditationSessions.sumOf { it.duration } + breathingSessions.sumOf { it.duration },
                    currentStreak = dashboardProgress.currentStreak,
                    weeklyGoal = dashboardProgress.weeklyGoal,
                    weeklyProgress = dashboardProgress.weeklyProgress,
                    totalDays = dashboardProgress.totalDays
                ),
                totalSessions = moodEntries.size + meditationSessions.size + breathingSessions.size,
                totalMinutes = meditationSessions.sumOf { it.duration } + breathingSessions.sumOf { it.duration },
                currentStreak = dashboardProgress.currentStreak,
                weeklyGoal = dashboardProgress.weeklyGoal,
                weeklyProgress = dashboardProgress.weeklyProgress,
                totalDays = dashboardProgress.totalDays
            )
            
            _moodData.value = MoodData(
                entries = moodEntries,
                totalEntries = moodEntries.size,
                averageMood = moodEntries.map { it.intensity }.average().toFloat(),
                recentMoods = moodEntries.take(5)
            )
            
            _meditationData.value = MeditationData(
                sessions = meditationSessions,
                totalSessions = meditationSessions.size,
                totalMinutes = meditationSessions.sumOf { it.duration },
                averageDuration = if (meditationSessions.isNotEmpty()) meditationSessions.map { it.duration }.average().toFloat() else 0f
            )
            
            _breathingData.value = BreathingData(
                sessions = breathingSessions,
                totalSessions = breathingSessions.size,
                totalMinutes = breathingSessions.sumOf { it.duration },
                averageDuration = if (breathingSessions.isNotEmpty()) breathingSessions.map { it.duration }.average().toFloat() else 0f
            )
            
            _insightsData.value = InsightsData(
                moodTrends = calculateMoodTrends(moodEntries),
                meditationProgress = calculateMeditationProgress(meditationSessions),
                breathingProgress = calculateBreathingProgress(breathingSessions),
                overallWellness = calculateOverallWellness(moodEntries, meditationSessions, breathingSessions)
            )
            
        } catch (e: Exception) {
            // Handle errors gracefully
            e.printStackTrace()
        }
    }
    
    /**
     * Add new mood entry and refresh data
     */
    suspend fun addMoodEntry(mood: String, intensity: Int, notes: String) {
        simpleDataManager.addMoodEntry(mood, intensity, notes)
        refreshAllData()
    }
    
    /**
     * Add new meditation session and refresh data
     */
    suspend fun addMeditationSession(title: String, duration: Int, category: String) {
        simpleDataManager.addMeditationSession(duration, title)
        refreshAllData()
    }
    
    /**
     * Add new breathing session and refresh data
     */
    suspend fun addBreathingSession(title: String, duration: Int, pattern: String) {
        simpleDataManager.addBreathingSession(duration, pattern)
        refreshAllData()
    }
    
    /**
     * Update user profile and refresh data
     */
    suspend fun updateUserProfile(name: String, age: String?, goal: String) {
        simpleDataManager.saveUserProfile(name, age ?: "", goal)
        refreshAllData()
    }
    
    // Private helper functions for calculations
    private fun calculateMoodTrends(entries: List<MoodEntryData>): MoodTrends {
        if (entries.isEmpty()) return MoodTrends()
        
        val recentEntries = entries.take(7) // Last 7 entries
        val averageMood = recentEntries.map { it.intensity }.average().toFloat()
        val moodVariation = recentEntries.map { it.intensity }.standardDeviation()
        
        return MoodTrends(
            averageMood = averageMood,
            moodVariation = moodVariation,
            dominantMood = recentEntries.groupBy { it.mood }
                .maxByOrNull { it.value.size }?.key ?: "Unknown",
            moodStability = if (moodVariation < 2.0) "Stable" else "Variable"
        )
    }
    
    private fun calculateMeditationProgress(sessions: List<MeditationSessionData>): MeditationProgress {
        if (sessions.isEmpty()) return MeditationProgress()
        
        val totalMinutes = sessions.sumOf { it.duration }
        val averageDuration = sessions.map { it.duration }.average().toFloat()
        val consistency = if (sessions.size >= 3) "Good" else "Improving"
        
        return MeditationProgress(
            totalMinutes = totalMinutes,
            averageDuration = averageDuration,
            consistency = consistency,
            totalSessions = sessions.size
        )
    }
    
    private fun calculateBreathingProgress(sessions: List<BreathingSessionData>): BreathingProgress {
        if (sessions.isEmpty()) return BreathingProgress()
        
        val totalMinutes = sessions.sumOf { it.duration }
        val averageDuration = sessions.map { it.duration }.average().toFloat()
        val consistency = if (sessions.size >= 3) "Good" else "Improving"
        
        return BreathingProgress(
            totalMinutes = totalMinutes,
            averageDuration = averageDuration,
            consistency = consistency,
            totalSessions = sessions.size
        )
    }
    
    private fun calculateOverallWellness(
        moodEntries: List<MoodEntryData>,
        meditationSessions: List<MeditationSessionData>,
        breathingSessions: List<BreathingSessionData>
    ): OverallWellness {
        val moodScore = if (moodEntries.isNotEmpty()) moodEntries.map { it.intensity }.average().toFloat() * 10 else 0f
        val meditationScore = if (meditationSessions.isNotEmpty()) (meditationSessions.size * 10).toFloat() else 0f
        val breathingScore = if (breathingSessions.isNotEmpty()) (breathingSessions.size * 10).toFloat() else 0f
        
        val overallScore = (moodScore + meditationScore + breathingScore) / 3
        
        return OverallWellness(
            overallScore = overallScore,
            moodScore = moodScore,
            meditationScore = meditationScore,
            breathingScore = breathingScore,
            recommendation = when {
                overallScore >= 80 -> "Excellent! Keep up the great work!"
                overallScore >= 60 -> "Good progress! Try to meditate more regularly."
                overallScore >= 40 -> "Getting there! Focus on consistent breathing exercises."
                else -> "Start with daily mood tracking and short meditation sessions."
            }
        )
    }
    
    private fun List<Int>.standardDeviation(): Float {
        if (isEmpty()) return 0f
        val mean = average()
        val variance = map { (it - mean) * (it - mean) }.average()
        return kotlin.math.sqrt(variance).toFloat()
    }
}

// Data classes for comprehensive app state
data class DashboardData(
    val userProfile: UserProfileData? = null,
    val progress: DashboardProgress = DashboardProgress(
        moodProgress = 0,
        meditationProgress = 0,
        breathingProgress = 0,
        totalSessions = 0,
        totalMinutes = 0,
        currentStreak = 0,
        weeklyGoal = 5,
        weeklyProgress = 0,
        totalDays = 0
    ),
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,
    val weeklyGoal: Int = 5,
    val weeklyProgress: Int = 0,
    val totalDays: Int = 0
)

data class MoodData(
    val entries: List<MoodEntryData> = emptyList(),
    val totalEntries: Int = 0,
    val averageMood: Float = 0f,
    val recentMoods: List<MoodEntryData> = emptyList()
)

data class MeditationData(
    val sessions: List<MeditationSessionData> = emptyList(),
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val averageDuration: Float = 0f
)

data class BreathingData(
    val sessions: List<BreathingSessionData> = emptyList(),
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val averageDuration: Float = 0f
)

data class InsightsData(
    val moodTrends: MoodTrends = MoodTrends(),
    val meditationProgress: MeditationProgress = MeditationProgress(),
    val breathingProgress: BreathingProgress = BreathingProgress(),
    val overallWellness: OverallWellness = OverallWellness()
)

data class MoodTrends(
    val averageMood: Float = 0f,
    val moodVariation: Float = 0f,
    val dominantMood: String = "Unknown",
    val moodStability: String = "Unknown"
)

data class MeditationProgress(
    val totalMinutes: Int = 0,
    val averageDuration: Float = 0f,
    val consistency: String = "Unknown",
    val totalSessions: Int = 0
)

data class BreathingProgress(
    val totalMinutes: Int = 0,
    val averageDuration: Float = 0f,
    val consistency: String = "Unknown",
    val totalSessions: Int = 0
)

data class OverallWellness(
    val overallScore: Float = 0f,
    val moodScore: Float = 0f,
    val meditationScore: Float = 0f,
    val breathingScore: Float = 0f,
    val recommendation: String = ""
)
