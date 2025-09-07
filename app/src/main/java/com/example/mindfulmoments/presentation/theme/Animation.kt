package com.example.mindfulmoments.presentation.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Breathing Phases
enum class BreathingPhase {
    INHALE, HOLD, EXHALE, REST
}

// Morphing Shapes
enum class MorphingShape {
    CIRCLE, SQUARE, TRIANGLE, STAR, HEART
}

// Simple animation functions that don't cause compilation errors
@Composable
fun rememberSpringAnimation(
    targetValue: Float,
    initialValue: Float = 0f
): State<Float> {
    return rememberInfiniteTransition().animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        )
    )
}

// Helper function for mood colors (moved from Color.kt to avoid conflicts)
fun getMoodColor(mood: String): Color {
    return when (mood.lowercase()) {
        "happy" -> Color(0xFFFFD700)
        "sad" -> Color(0xFF87CEEB)
        "angry" -> Color(0xFFFF6B6B)
        "anxious" -> Color(0xFFFFB74D)
        "calm" -> Color(0xFF81C784)
        "excited" -> Color(0xFFFF8A80)
        else -> Color(0xFFBDBDBD)
    }
}
