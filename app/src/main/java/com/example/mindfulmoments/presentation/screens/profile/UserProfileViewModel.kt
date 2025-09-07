package com.example.mindfulmoments.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(UserProfileUiState())
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()
    
    fun saveUserProfile(name: String, age: String, goal: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // For now, simulate saving to database
                kotlinx.coroutines.delay(500) // Simulate network delay
                val userId = System.currentTimeMillis() // Generate fake user ID
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isProfileSaved = true,
                    userId = userId
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to save profile"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    fun resetProfileSaved() {
        _uiState.value = _uiState.value.copy(isProfileSaved = false)
    }
}

data class UserProfileUiState(
    val isLoading: Boolean = false,
    val isProfileSaved: Boolean = false,
    val userId: Long = 0L,
    val error: String? = null
)
