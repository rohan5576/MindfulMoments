package com.example.mindfulmoments.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Revolutionary Material Design 4.0+ Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = MindfulColors.Primary,
    primaryContainer = MindfulColors.PrimaryContainer,
    secondary = MindfulColors.Secondary,
    secondaryContainer = MindfulColors.SecondaryContainer,
    tertiary = MindfulColors.Tertiary,
    tertiaryContainer = MindfulColors.TertiaryContainer,
    
    // Surface Colors - Glassmorphism & Neumorphism
    surface = MindfulColors.Surface,
    surfaceVariant = MindfulColors.SurfaceVariant,
    surfaceDim = MindfulColors.SurfaceDim,
    
    // Background Colors - Dynamic Gradient Meshes
    background = MindfulColors.Background,
    
    // Text Colors
    onPrimary = MindfulColors.TextInverse,
    onPrimaryContainer = MindfulColors.TextPrimary,
    onSecondary = MindfulColors.TextInverse,
    onSecondaryContainer = MindfulColors.TextPrimary,
    onTertiary = MindfulColors.TextInverse,
    onTertiaryContainer = MindfulColors.TextPrimary,
    onSurface = MindfulColors.TextPrimary,
    onSurfaceVariant = MindfulColors.TextSecondary,
    onBackground = MindfulColors.TextPrimary,
    
    // Error Colors
    error = MindfulColors.Error,
    onError = MindfulColors.TextInverse,
    errorContainer = MindfulColors.Error.copy(alpha = 0.1f),
    onErrorContainer = MindfulColors.Error,
    
    // Success Colors
    outline = MindfulColors.Success,
    outlineVariant = MindfulColors.Success.copy(alpha = 0.5f)
)

private val DarkColorScheme = darkColorScheme(
    primary = MindfulColors.Primary,
    primaryContainer = MindfulColors.PrimaryContainer,
    secondary = MindfulColors.Secondary,
    secondaryContainer = MindfulColors.SecondaryContainer,
    tertiary = MindfulColors.Tertiary,
    tertiaryContainer = MindfulColors.TertiaryContainer,
    
    // Surface Colors - Glassmorphism & Neumorphism
    surface = MindfulColors.Surface.copy(alpha = 0.1f),
    surfaceVariant = MindfulColors.SurfaceVariant.copy(alpha = 0.2f),
    surfaceDim = MindfulColors.SurfaceDim.copy(alpha = 0.3f),
    
    // Background Colors - Dynamic Gradient Meshes
    background = MindfulColors.BackgroundVariant,
    
    // Text Colors
    onPrimary = MindfulColors.TextInverse,
    onPrimaryContainer = MindfulColors.TextInverse,
    onSecondary = MindfulColors.TextInverse,
    onSecondaryContainer = MindfulColors.TextInverse,
    onTertiary = MindfulColors.TextInverse,
    onTertiaryContainer = MindfulColors.TextInverse,
    onSurface = MindfulColors.TextInverse,
    onSurfaceVariant = MindfulColors.TextInverse.copy(alpha = 0.8f),
    onBackground = MindfulColors.TextInverse,
    
    // Error Colors
    error = MindfulColors.Error,
    onError = MindfulColors.TextInverse,
    errorContainer = MindfulColors.Error.copy(alpha = 0.2f),
    onErrorContainer = MindfulColors.TextInverse,
    
    // Success Colors
    outline = MindfulColors.Success,
    outlineVariant = MindfulColors.Success.copy(alpha = 0.6f)
)

// Dynamic Color Scheme for Android 12+
private val DynamicLightColorScheme = lightColorScheme(
    primary = MindfulColors.Primary,
    primaryContainer = MindfulColors.PrimaryContainer,
    secondary = MindfulColors.Secondary,
    secondaryContainer = MindfulColors.SecondaryContainer,
    tertiary = MindfulColors.Tertiary,
    tertiaryContainer = MindfulColors.TertiaryContainer,
    
    // Surface Colors - Glassmorphism & Neumorphism
    surface = MindfulColors.Surface,
    surfaceVariant = MindfulColors.SurfaceVariant,
    surfaceDim = MindfulColors.SurfaceDim,
    
    // Background Colors - Dynamic Gradient Meshes
    background = MindfulColors.Background,
    
    // Text Colors
    onPrimary = MindfulColors.TextInverse,
    onPrimaryContainer = MindfulColors.TextPrimary,
    onSecondary = MindfulColors.TextInverse,
    onSecondaryContainer = MindfulColors.TextPrimary,
    onTertiary = MindfulColors.TextInverse,
    onTertiaryContainer = MindfulColors.TextPrimary,
    onSurface = MindfulColors.TextPrimary,
    onSurfaceVariant = MindfulColors.TextSecondary,
    onBackground = MindfulColors.TextPrimary,
    
    // Error Colors
    error = MindfulColors.Error,
    onError = MindfulColors.TextInverse,
    errorContainer = MindfulColors.Error.copy(alpha = 0.1f),
    onErrorContainer = MindfulColors.Error,
    
    // Success Colors
    outline = MindfulColors.Success,
    outlineVariant = MindfulColors.Success.copy(alpha = 0.5f)
)

private val DynamicDarkColorScheme = darkColorScheme(
    primary = MindfulColors.Primary,
    primaryContainer = MindfulColors.PrimaryContainer,
    secondary = MindfulColors.Secondary,
    secondaryContainer = MindfulColors.SecondaryContainer,
    tertiary = MindfulColors.Tertiary,
    tertiaryContainer = MindfulColors.TertiaryContainer,
    
    // Surface Colors - Glassmorphism & Neumorphism
    surface = MindfulColors.Surface.copy(alpha = 0.1f),
    surfaceVariant = MindfulColors.SurfaceVariant.copy(alpha = 0.2f),
    surfaceDim = MindfulColors.SurfaceDim.copy(alpha = 0.3f),
    
    // Background Colors - Dynamic Gradient Meshes
    background = MindfulColors.BackgroundVariant,
    
    // Text Colors
    onPrimary = MindfulColors.TextInverse,
    onPrimaryContainer = MindfulColors.TextInverse,
    onSecondary = MindfulColors.TextInverse,
    onSecondaryContainer = MindfulColors.TextInverse,
    onTertiary = MindfulColors.TextInverse,
    onTertiaryContainer = MindfulColors.TextInverse,
    onSurface = MindfulColors.TextInverse,
    onSurfaceVariant = MindfulColors.TextInverse.copy(alpha = 0.8f),
    onBackground = MindfulColors.TextInverse,
    
    // Error Colors
    error = MindfulColors.Error,
    onError = MindfulColors.TextInverse,
    errorContainer = MindfulColors.Error.copy(alpha = 0.2f),
    onErrorContainer = MindfulColors.TextInverse,
    
    // Success Colors
    outline = MindfulColors.Success,
    outlineVariant = MindfulColors.Success.copy(alpha = 0.6f)
)

@Composable
fun MindfulMomentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Make the system bar transparent
            window.statusBarColor = colorScheme.primary.toArgb()
            // Make the navigation bar transparent
            window.navigationBarColor = colorScheme.primary.toArgb()
            // Enable edge-to-edge
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MindfulMomentsTypography,
        content = content
    )
}

// Mood-Based Theme Variations
@Composable
fun MindfulMomentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    currentMood: String = "neutral",
    content: @Composable () -> Unit
) {
    val moodColor = getMoodColor(currentMood)
    
    val moodColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context).copy(
                    primary = moodColor,
                    primaryContainer = moodColor.copy(alpha = 0.8f)
                )
            } else {
                dynamicLightColorScheme(context).copy(
                    primary = moodColor,
                    primaryContainer = moodColor.copy(alpha = 0.8f)
                )
            }
        }
        darkTheme -> DarkColorScheme.copy(
            primary = moodColor,
            primaryContainer = moodColor.copy(alpha = 0.8f)
        )
        else -> LightColorScheme.copy(
            primary = moodColor,
            primaryContainer = moodColor.copy(alpha = 0.8f)
        )
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Make the system bar transparent
            window.statusBarColor = moodColorScheme.primary.toArgb()
            // Make the navigation bar transparent
            window.navigationBarColor = moodColorScheme.primary.toArgb()
            // Enable edge-to-edge
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
    
    MaterialTheme(
        colorScheme = moodColorScheme,
        typography = MindfulMomentsTypography,
        content = content
    )
}

// Breathing Exercise Theme
@Composable
fun MindfulMomentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    breathingCycle: Int = 0,
    content: @Composable () -> Unit
) {
    val chakraColor = getChakraColor(breathingCycle)
    
    val breathingColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context).copy(
                    primary = chakraColor,
                    primaryContainer = chakraColor.copy(alpha = 0.8f),
                    background = chakraColor.copy(alpha = 0.1f)
                )
            } else {
                dynamicLightColorScheme(context).copy(
                    primary = chakraColor,
                    primaryContainer = chakraColor.copy(alpha = 0.8f),
                    background = chakraColor.copy(alpha = 0.05f)
                )
            }
        }
        darkTheme -> DarkColorScheme.copy(
            primary = chakraColor,
            primaryContainer = chakraColor.copy(alpha = 0.8f),
            background = chakraColor.copy(alpha = 0.1f)
        )
        else -> LightColorScheme.copy(
            primary = chakraColor,
            primaryContainer = chakraColor.copy(alpha = 0.8f),
            background = chakraColor.copy(alpha = 0.05f)
        )
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Make the system bar transparent
            window.statusBarColor = breathingColorScheme.primary.toArgb()
            // Make the navigation bar transparent
            window.navigationBarColor = breathingColorScheme.primary.toArgb()
            // Enable edge-to-edge
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
    
    MaterialTheme(
        colorScheme = breathingColorScheme,
        typography = MindfulMomentsTypography,
        content = content
    )
}
