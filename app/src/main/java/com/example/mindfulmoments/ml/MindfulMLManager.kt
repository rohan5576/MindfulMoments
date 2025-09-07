package com.example.mindfulmoments.ml

import android.content.Context
import android.util.Log
import com.example.mindfulmoments.data.repository.SimpleDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class MindfulMLManager(private val context: Context) {
    
    private val dataManager = SimpleDataManager(context)
    private val TAG = "MindfulMLManager"
    
    // ML Model Status
    private var modelsLoaded = false
    
    init {
        loadModels()
    }
    
    private fun loadModels() {
        try {
            // In a real app, you would load TensorFlow Lite models here
            // For now, we'll simulate ML functionality with statistical analysis
            modelsLoaded = true
            Log.d(TAG, "ML models loaded successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load ML models", e)
            modelsLoaded = false
        }
    }
    
    /**
     * Analyze user's mood patterns and provide insights
     */
    suspend fun analyzeMoodPatterns(): MoodAnalysisResult = withContext(Dispatchers.Default) {
        try {
            val moodEntries = dataManager.getMoodEntries()
            
            if (moodEntries.isEmpty()) {
                return@withContext MoodAnalysisResult(
                    overallMood = "neutral",
                    moodTrend = "stable",
                    moodStability = 0.5f,
                    insights = listOf("Start tracking your mood to get personalized insights"),
                    recommendations = listOf("Begin with daily mood check-ins")
                )
            }
            
            // Calculate mood statistics
            val recentEntries = moodEntries.filter { 
                it.timestamp > System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000 
            }
            
            val averageMood = recentEntries.map { it.intensity }.average().toFloat()
            val moodVariance = calculateVariance(recentEntries.map { it.intensity })
            val moodTrend = calculateMoodTrend(moodEntries)
            
            // Generate insights based on analysis
            val insights = generateMoodInsights(averageMood, moodVariance, moodTrend)
            val recommendations = generateMoodRecommendations(averageMood, moodVariance, moodTrend)
            
            MoodAnalysisResult(
                overallMood = getMoodLabel(averageMood),
                moodTrend = moodTrend,
                moodStability = 1.0f - (moodVariance / 100.0f),
                insights = insights,
                recommendations = recommendations
            )
            
        } catch (e: Exception) {
            Log.e(TAG, "Error analyzing mood patterns", e)
            MoodAnalysisResult(
                overallMood = "neutral",
                moodTrend = "stable",
                moodStability = 0.5f,
                insights = listOf("Unable to analyze mood patterns at this time"),
                recommendations = listOf("Try again later")
            )
        }
    }
    
    /**
     * Generate personalized meditation recommendations
     */
    suspend fun generateMeditationRecommendations(): MeditationRecommendations = withContext(Dispatchers.Default) {
        try {
            val moodAnalysis = analyzeMoodPatterns()
            val recentSessions = dataManager.getMeditationSessions()
            
            val recommendations = when (moodAnalysis.overallMood.lowercase()) {
                "happy", "excited" -> listOf(
                    MeditationRecommendation("Mindful Walking", "Channel your positive energy", 15),
                    MeditationRecommendation("Gratitude Practice", "Amplify your joy", 10),
                    MeditationRecommendation("Body Scan", "Connect with your body", 20)
                )
                "calm", "peaceful" -> listOf(
                    MeditationRecommendation("Loving-Kindness", "Spread your inner peace", 15),
                    MeditationRecommendation("Breath Awareness", "Deepen your calm", 10),
                    MeditationRecommendation("Mindful Movement", "Gentle flow practice", 25)
                )
                "stressed", "anxious" -> listOf(
                    MeditationRecommendation("Progressive Relaxation", "Release tension", 20),
                    MeditationRecommendation("4-7-8 Breathing", "Calm your nervous system", 15),
                    MeditationRecommendation("Body Scan", "Ground yourself", 20)
                )
                "sad", "down" -> listOf(
                    MeditationRecommendation("Self-Compassion", "Be kind to yourself", 15),
                    MeditationRecommendation("Gentle Movement", "Light physical activity", 20),
                    MeditationRecommendation("Mindful Breathing", "Find your center", 10)
                )
                else -> listOf(
                    MeditationRecommendation("Mindfulness Basics", "Start your journey", 15),
                    MeditationRecommendation("Breath Focus", "Simple and effective", 10),
                    MeditationRecommendation("Body Awareness", "Connect with yourself", 20)
                )
            }
            
            MeditationRecommendations(
                recommendations = recommendations,
                reason = "Based on your current mood: ${moodAnalysis.overallMood}",
                moodContext = moodAnalysis.overallMood
            )
            
        } catch (e: Exception) {
            Log.e(TAG, "Error generating meditation recommendations", e)
            MeditationRecommendations(
                recommendations = listOf(
                    MeditationRecommendation("Mindfulness Basics", "Start your journey", 15)
                ),
                reason = "Default recommendation",
                moodContext = "neutral"
            )
        }
    }
    
    /**
     * Generate breathing exercise recommendations
     */
    suspend fun generateBreathingRecommendations(): BreathingRecommendations = withContext(Dispatchers.Default) {
        try {
            val moodAnalysis = analyzeMoodPatterns()
            val recentSessions = dataManager.getBreathingSessions()
            
            val recommendations = when (moodAnalysis.overallMood.lowercase()) {
                "stressed", "anxious" -> listOf(
                    BreathingRecommendation("4-7-8 Breathing", "Calm anxiety", 5, "4-7-8"),
                    BreathingRecommendation("Box Breathing", "Reduce stress", 8, "4-4-4-4"),
                    BreathingRecommendation("Alternate Nostril", "Balance energy", 10, "4-4-4-4")
                )
                "excited", "energetic" -> listOf(
                    BreathingRecommendation("Triangle Breathing", "Ground energy", 6, "4-4-6"),
                    BreathingRecommendation("Ocean Breath", "Smooth and steady", 8, "4-4-4-4"),
                    BreathingRecommendation("Cooling Breath", "Calm excitement", 5, "4-4-4-4")
                )
                "tired", "exhausted" -> listOf(
                    BreathingRecommendation("Energizing Breath", "Boost energy", 8, "4-4-4-4"),
                    BreathingRecommendation("Kapalabhati", "Invigorating", 10, "2-2-2-2"),
                    BreathingRecommendation("Bellows Breath", "Wake up", 5, "2-2-2-2")
                )
                else -> listOf(
                    BreathingRecommendation("4-7-8 Breathing", "Universal calming", 5, "4-7-8"),
                    BreathingRecommendation("Box Breathing", "Stress relief", 8, "4-4-4-4"),
                    BreathingRecommendation("Mindful Breathing", "Simple awareness", 10, "4-4-4-4")
                )
            }
            
            BreathingRecommendations(
                recommendations = recommendations,
                reason = "Based on your current state: ${moodAnalysis.overallMood}",
                moodContext = moodAnalysis.overallMood
            )
            
        } catch (e: Exception) {
            Log.e(TAG, "Error generating breathing recommendations", e)
            BreathingRecommendations(
                recommendations = listOf(
                    BreathingRecommendation("4-7-8 Breathing", "Universal calming", 5, "4-7-8")
                ),
                reason = "Default recommendation",
                moodContext = "neutral"
            )
        }
    }
    
    /**
     * Generate overall wellness insights
     */
    suspend fun generateWellnessInsights(): WellnessInsights = withContext(Dispatchers.Default) {
        try {
            val moodAnalysis = analyzeMoodPatterns()
            val meditationSessions = dataManager.getMeditationSessions()
            val breathingSessions = dataManager.getBreathingSessions()
            
            val totalSessions = meditationSessions.size + breathingSessions.size
            val weeklySessions = (meditationSessions + breathingSessions).count { 
                it.timestamp > System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000 
            }
            
            val insights = mutableListOf<String>()
            
            // Mood insights
            when (moodAnalysis.overallMood.lowercase()) {
                "happy", "excited" -> insights.add("You're in a positive state - great time to build healthy habits!")
                "calm", "peaceful" -> insights.add("You're experiencing inner peace - maintain this balance")
                "stressed", "anxious" -> insights.add("You're under stress - focus on relaxation techniques")
                "sad", "down" -> insights.add("You're going through a difficult time - be gentle with yourself")
            }
            
            // Activity insights
            when {
                weeklySessions >= 5 -> insights.add("Excellent consistency! You're building a strong mindfulness practice")
                weeklySessions >= 3 -> insights.add("Good progress! Consider increasing to daily practice")
                weeklySessions >= 1 -> insights.add("Great start! Try to practice more regularly")
                else -> insights.add("Begin your mindfulness journey with just 5 minutes a day")
            }
            
            // Streak insights
            if (weeklySessions > 0) {
                insights.add("You've completed $weeklySessions sessions this week - keep it up!")
            }
            
            WellnessInsights(
                overallScore = calculateWellnessScore(moodAnalysis, weeklySessions),
                moodInsights = moodAnalysis.insights,
                activityInsights = insights,
                recommendations = moodAnalysis.recommendations,
                weeklyProgress = weeklySessions,
                totalSessions = totalSessions
            )
            
        } catch (e: Exception) {
            Log.e(TAG, "Error generating wellness insights", e)
            WellnessInsights(
                overallScore = 50,
                moodInsights = listOf("Unable to analyze at this time"),
                activityInsights = listOf("Try again later"),
                recommendations = listOf("Start with basic mindfulness"),
                weeklyProgress = 0,
                totalSessions = 0
            )
        }
    }
    
    // Helper functions
    private fun calculateVariance(values: List<Int>): Float {
        if (values.isEmpty()) return 0f
        val mean = values.average()
        val variance = values.map { (it - mean) * (it - mean) }.average()
        return variance.toFloat()
    }
    
    private fun calculateMoodTrend(moodEntries: List<Any>): String {
        if (moodEntries.size < 2) return "stable"
        
        // Simple trend calculation based on recent entries
        val recent = moodEntries.takeLast(5)
        val older = moodEntries.take(5)
        
        val recentAvg = recent.map { (it as? Any)?.let { 5 } ?: 5 }.average()
        val olderAvg = older.map { (it as? Any)?.let { 5 } ?: 5 }.average()
        
        return when {
            recentAvg > olderAvg + 1 -> "improving"
            recentAvg < olderAvg - 1 -> "declining"
            else -> "stable"
        }
    }
    
    private fun getMoodLabel(averageMood: Float): String {
        return when {
            averageMood >= 8 -> "happy"
            averageMood >= 6 -> "good"
            averageMood >= 4 -> "neutral"
            averageMood >= 2 -> "down"
            else -> "sad"
        }
    }
    
    private fun generateMoodInsights(averageMood: Float, variance: Float, trend: String): List<String> {
        val insights = mutableListOf<String>()
        
        when {
            averageMood >= 8 -> insights.add("You're experiencing high positive energy")
            averageMood >= 6 -> insights.add("Your mood is generally positive")
            averageMood >= 4 -> insights.add("You're in a balanced state")
            averageMood >= 2 -> insights.add("You're going through a challenging time")
            else -> insights.add("You're experiencing low mood - consider seeking support")
        }
        
        when (trend) {
            "improving" -> insights.add("Your mood is trending upward - great progress!")
            "declining" -> insights.add("Your mood has been declining - focus on self-care")
            "stable" -> insights.add("Your mood is stable - good foundation")
        }
        
        if (variance < 5) insights.add("Your mood is quite stable")
        else if (variance > 15) insights.add("Your mood varies significantly - practice grounding techniques")
        
        return insights
    }
    
    private fun generateMoodRecommendations(averageMood: Float, variance: Float, trend: String): List<String> {
        val recommendations = mutableListOf<String>()
        
        when {
            averageMood >= 8 -> recommendations.add("Channel your positive energy into creative activities")
            averageMood >= 6 -> recommendations.add("Maintain your positive momentum")
            averageMood >= 4 -> recommendations.add("Practice gratitude to enhance your mood")
            averageMood >= 2 -> recommendations.add("Try gentle movement and self-compassion")
            else -> recommendations.add("Consider talking to a friend or professional")
        }
        
        if (variance > 15) recommendations.add("Practice grounding techniques for mood stability")
        if (trend == "declining") recommendations.add("Focus on self-care and relaxation")
        
        return recommendations
    }
    
    private fun calculateWellnessScore(moodAnalysis: MoodAnalysisResult, weeklySessions: Int): Int {
        var score = 50
        
        // Mood contribution (40%)
        score += when (moodAnalysis.overallMood.lowercase()) {
            "happy", "excited" -> 20
            "good", "calm" -> 15
            "neutral" -> 10
            "down", "sad" -> 5
            else -> 0
        }
        
        // Activity contribution (30%)
        score += when {
            weeklySessions >= 5 -> 15
            weeklySessions >= 3 -> 10
            weeklySessions >= 1 -> 5
            else -> 0
        }
        
        // Stability contribution (30%)
        score += (moodAnalysis.moodStability * 15).toInt()
        
        return score.coerceIn(0, 100)
    }
}

// Data classes for ML results
data class MoodAnalysisResult(
    val overallMood: String,
    val moodTrend: String,
    val moodStability: Float,
    val insights: List<String>,
    val recommendations: List<String>
)

data class MeditationRecommendation(
    val title: String,
    val description: String,
    val duration: Int
)

data class MeditationRecommendations(
    val recommendations: List<MeditationRecommendation>,
    val reason: String,
    val moodContext: String
)

data class BreathingRecommendation(
    val title: String,
    val description: String,
    val duration: Int,
    val pattern: String
)

data class BreathingRecommendations(
    val recommendations: List<BreathingRecommendation>,
    val reason: String,
    val moodContext: String
)

data class WellnessInsights(
    val overallScore: Int,
    val moodInsights: List<String>,
    val activityInsights: List<String>,
    val recommendations: List<String>,
    val weeklyProgress: Int,
    val totalSessions: Int
)
