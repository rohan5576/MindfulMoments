package com.example.mindfulmoments.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Premium Typography System using Material3
val MindfulMomentsTypography = Typography(
    // Display Styles
    displayLarge = Typography().displayLarge.copy(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.Bold
    ),
    
    displayMedium = Typography().displayMedium.copy(
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.SemiBold
    ),
    
    displaySmall = Typography().displaySmall.copy(
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.SemiBold
    ),
    
    // Headline Styles
    headlineLarge = Typography().headlineLarge.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold
    ),
    
    headlineMedium = Typography().headlineMedium.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.SemiBold
    ),
    
    headlineSmall = Typography().headlineSmall.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold
    ),
    
    // Title Styles
    titleLarge = Typography().titleLarge.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold
    ),
    
    titleMedium = Typography().titleMedium.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    
    titleSmall = Typography().titleSmall.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    
    // Body Styles
    bodyLarge = Typography().bodyLarge.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    
    bodyMedium = Typography().bodyMedium.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    
    bodySmall = Typography().bodySmall.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    
    // Label Styles
    labelLarge = Typography().labelLarge.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    
    labelMedium = Typography().labelMedium.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    
    labelSmall = Typography().labelSmall.copy(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium
    )
)
