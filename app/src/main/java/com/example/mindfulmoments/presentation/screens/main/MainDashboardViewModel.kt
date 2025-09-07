package com.example.mindfulmoments.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindfulmoments.data.repository.SimpleDataManager
import com.example.mindfulmoments.data.repository.DashboardProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.content.Context

class MainDashboardViewModel(private val context: Context) : ViewModel() {
    
    private val dataManager = SimpleDataManager(context)
    
    private val _uiState = MutableStateFlow(MainDashboardUiState())
    val uiState: StateFlow<MainDashboardUiState> = _uiState.asStateFlow()
    
    init {
        loadUserProfile()
        loadDashboardProgress()
    }
    
    private fun loadUserProfile() {
        viewModelScope.launch {
            try {
                val userProfile = dataManager.getUserProfile()
                _uiState.value = _uiState.value.copy(
                    userName = userProfile?.name ?: "User",
                    userGoal = userProfile?.goal ?: "Mindfulness"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    userName = "User",
                    userGoal = "Mindfulness"
                )
            }
        }
    }
    
    private fun loadDashboardProgress() {
        viewModelScope.launch {
            try {
                val progress = dataManager.getDashboardProgress()
                _uiState.value = _uiState.value.copy(
                    moodProgress = progress.moodProgress,
                    meditationProgress = progress.meditationProgress,
                    breathingProgress = progress.breathingProgress,
                    totalSessions = progress.totalSessions,
                    totalMinutes = progress.totalMinutes,
                    currentStreak = progress.currentStreak,
                    weeklyGoal = progress.weeklyGoal,
                    weeklyProgress = progress.weeklyProgress,
                    totalDays = progress.totalDays
                )
            } catch (e: Exception) {
                // Handle error - use default values
                _uiState.value = _uiState.value.copy(
                    moodProgress = 0,
                    meditationProgress = 0,
                    breathingProgress = 0,
                    totalSessions = 0,
                    totalMinutes = 0,
                    currentStreak = 0,
                    weeklyGoal = 0,
                    weeklyProgress = 0,
                    totalDays = 0
                )
            }
        }
    }
    
    fun addMoodEntry(moodType: String, intensity: Int, notes: String?) {
        viewModelScope.launch {
            try {
                dataManager.addMoodEntry(moodType, intensity, notes)
                loadDashboardProgress() // Refresh progress
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun addMeditationSession(duration: Int, type: String) {
        viewModelScope.launch {
            try {
                dataManager.addMeditationSession(duration, type)
                loadDashboardProgress() // Refresh progress
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun addBreathingSession(duration: Int, pattern: String) {
        viewModelScope.launch {
            try {
                dataManager.addBreathingSession(duration, pattern)
                loadDashboardProgress() // Refresh progress
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun refreshData() {
        loadUserProfile()
        loadDashboardProgress()
    }
}

data class MainDashboardUiState(
    val userName: String = "",
    val userGoal: String = "",
    val moodProgress: Int = 0,
    val meditationProgress: Int = 0,
    val breathingProgress: Int = 0,
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,
    val weeklyGoal: Int = 5,
    val weeklyProgress: Int = 0,
    val totalDays: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)
