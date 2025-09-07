package com.example.mindfulmoments.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Revolutionary Color System with Dynamic Color Psychology
object MindfulColors {
    
    // Primary Color Palette - Indigo with Depth
    val Primary = Color(0xFF6366F1)
    val PrimaryContainer = Color(0xFF4F46E5)
    val PrimaryVariant = Color(0xFF4338CA)
    
    // Secondary Color Palette - Cyan with Energy
    val Secondary = Color(0xFF06B6D4)
    val SecondaryContainer = Color(0xFF0891B2)
    val SecondaryVariant = Color(0xFF0E7490)
    
    // Tertiary Color Palette - Purple for Premium Feel
    val Tertiary = Color(0xFF8B5CF6)
    val TertiaryContainer = Color(0xFF7C3AED)
    val TertiaryVariant = Color(0xFF6D28D9)
    
    // Surface Colors - Glassmorphism & Neumorphism
    val Surface = Color(0xFFFAFAFC)
    val SurfaceVariant = Color(0x40FFFFFF) // Glassmorphic surface
    val SurfaceDim = Color(0xFFF0F0F3) // Neumorphism background
    
    // Background Colors - Dynamic Gradient Meshes
    val Background = Color(0xFF667EEA)
    val BackgroundVariant = Color(0xFF764BA2)
    
    // Glassmorphism Palette - Semi-transparent surfaces
    val GlassPrimary = Color(0x60FFFFFF) // 60% opacity
    val GlassSecondary = Color(0x80FFFFFF) // 80% opacity
    val GlassAccent = Color(0x40FFFFFF) // 40% opacity
    
    // Neumorphism Accents - Soft shadows and highlights
    val NeumorphismLight = Color(0xFFFFFFFF)
    val NeumorphismDark = Color(0xFFE0E0E3)
    val NeumorphismShadow = Color(0xFFB8B8BB)
    
    // Aurora Color Schemes - Dynamic northern lights-inspired gradients
    val AuroraGreen = Color(0xFF00FF88)
    val AuroraBlue = Color(0xFF00D4FF)
    val AuroraPurple = Color(0xFF8B5CF6)
    val AuroraPink = Color(0xFFEC4899)
    
    // Mood-Based Colors - Dynamic color psychology
    val MoodHappy = Color(0xFFFFD700) // Golden yellow
    val MoodCalm = Color(0xFF87CEEB) // Sky blue
    val MoodStressed = Color(0xFFFF6B6B) // Coral red
    val MoodSad = Color(0xFF6B73FF) // Indigo blue
    val MoodAngry = Color(0xFFFF4500) // Orange red
    val MoodNeutral = Color(0xFF9CA3AF) // Gray
    
    // Breathing Exercise Colors - Chakra spectrum
    val ChakraRoot = Color(0xFFFF0000) // Red
    val ChakraSacral = Color(0xFFFF7F00) // Orange
    val ChakraSolar = Color(0xFFFFFF00) // Yellow
    val ChakraHeart = Color(0xFF00FF00) // Green
    val ChakraThroat = Color(0xFF0000FF) // Blue
    val ChakraThirdEye = Color(0xFF4B0082) // Indigo
    val ChakraCrown = Color(0xFF9400D3) // Violet
    
    // Meditation Environment Colors
    val ForestGreen = Color(0xFF228B22)
    val OceanBlue = Color(0xFF1E90FF)
    val SunsetOrange = Color(0xFFFF8C00)
    val NightPurple = Color(0xFF4B0082)
    val SpaceBlack = Color(0xFF000000)
    
    // Success & Achievement Colors
    val Success = Color(0xFF10B981)
    val Warning = Color(0xFFF59E0B)
    val Error = Color(0xFFEF4444)
    val Info = Color(0xFF3B82F6)
    
    // Text Colors
    val TextPrimary = Color(0xFF1F2937)
    val TextSecondary = Color(0xFF6B7280)
    val TextTertiary = Color(0xFF9CA3AF)
    val TextInverse = Color(0xFFFFFFFF)
    
    // Dynamic Background Gradients
    val MorningGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFB347), // Warm orange
            Color(0xFFFFD700)  // Golden yellow
        )
    )
    
    val AfternoonGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF87CEEB), // Sky blue
            Color(0xFF98FB98)  // Pale green
        )
    )
    
    val EveningGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFF69B4), // Hot pink
            Color(0xFF9370DB)  // Medium purple
        )
    )
    
    val NightGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF191970), // Midnight blue
            Color(0xFF4B0082)  // Indigo
        )
    )
    
    val AuroraGradient = Brush.verticalGradient(
        colors = listOf(
            AuroraGreen,
            AuroraBlue,
            AuroraPurple,
            AuroraPink
        )
    )
    
    // Glassmorphism Gradients
    val GlassGradient = Brush.verticalGradient(
        colors = listOf(
            GlassPrimary,
            GlassSecondary,
            GlassAccent
        )
    )
    
    // Neumorphism Gradients
    val NeumorphismGradient = Brush.radialGradient(
        colors = listOf(
            NeumorphismLight,
            NeumorphismDark
        )
    )
}

// Extension function to get chakra color by breathing cycle
fun getChakraColor(cycle: Int): Color {
    val chakraColors = listOf(
        MindfulColors.ChakraRoot,
        MindfulColors.ChakraSacral,
        MindfulColors.ChakraSolar,
        MindfulColors.ChakraHeart,
        MindfulColors.ChakraThroat,
        MindfulColors.ChakraThirdEye,
        MindfulColors.ChakraCrown
    )
    return chakraColors[cycle % chakraColors.size]
}
