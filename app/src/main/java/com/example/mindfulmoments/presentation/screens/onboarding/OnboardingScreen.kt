package com.example.mindfulmoments.presentation.screens.onboarding

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onOnboardingComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentPage by remember { mutableStateOf(0) }
    val totalPages = 3

    // Animations
    val infiniteTransition = rememberInfiniteTransition(label = "onboarding")

    val breathingScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = CubicBezierEasing(0.4f, 0f, 0.6f, 1f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = CubicBezierEasing(0.4f, 0f, 0.6f, 1f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    // Mood glow with advanced easing
    val moodGlow by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mood_glow"
    )


    Box(modifier = modifier.fillMaxSize()) {
        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF0F0F23),
                            Color(0xFF1A1A2E),
                            Color(0xFF16213E),
                            Color(0xFF0F3460)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo with breathing and glow
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .scale(breathingScale)
                    .graphicsLayer(alpha = 0.9f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF6366F1),
                                    Color(0xFF8B5CF6),
                                    Color(0xFFEC4899)
                                )
                            )
                        )
                        .shadow(
                            elevation = 25.dp,
                            shape = CircleShape,
                            ambientColor = Color(0xFF6366F1).copy(alpha = glowAlpha),
                            spotColor = Color(0xFFEC4899).copy(alpha = glowAlpha)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🧘", fontSize = 48.sp)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Page content
            AnimatedContent(
                targetState = currentPage,
                transitionSpec = {
                    slideInHorizontally { if (targetState > initialState) it else -it } +
                            fadeIn() togetherWith
                            slideOutHorizontally { if (targetState > initialState) -it else it } +
                            fadeOut()
                },
                label = "onboarding_content"
            ) { page ->
                when (page) {
                    0 -> RevolutionaryWelcomePage()
                    1 -> RevolutionaryPermissionsPage()
                    2 -> RevolutionaryMoodPage()
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // pushes nav down

            // Navigation
            RevolutionaryNavigation(
                currentPage = currentPage,
                totalPages = totalPages,
                glowAlpha = glowAlpha,
                onNext = {
                    if (currentPage < totalPages - 1) {
                        currentPage++
                        Log.d("OnboardingScreen", "Next clicked, page: $currentPage")
                    } else {
                        onOnboardingComplete()
                    }
                },
                onPrevious = { if (currentPage > 0) currentPage-- }
            )
        }
    }
}

@Composable
private fun RevolutionaryWelcomePage() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome to",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Mindful Moments",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "Your personal AI-powered mindfulness companion",
            fontSize = 18.sp,
            color = Color.White.copy(alpha = 0.9f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )

        RevolutionaryFeatureCard(
            icon = "🎯",
            title = "AI Personalization",
            description = "Get recommendations tailored to your mood and preferences"
        )
        Spacer(modifier = Modifier.height(16.dp))
        RevolutionaryFeatureCard(
            icon = "🔒",
            title = "100% Offline",
            description = "All your data stays on your device for complete privacy"
        )
    }
}

@Composable
private fun RevolutionaryPermissionsPage() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RevolutionaryPermissionCard(
            icon = "🎵",
            title = "Audio",
            description = "Play meditation sessions and breathing exercises"
        )
        Spacer(modifier = Modifier.height(16.dp))
        RevolutionaryPermissionCard(
            icon = "📱",
            title = "Storage",
            description = "Save your progress and meditation sessions locally"
        )
    }
}

@Composable
private fun RevolutionaryMoodPage() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "How are you feeling?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Let's personalize your experience based on your current emotional state",
            fontSize = 18.sp,
            color = Color.White.copy(alpha = 0.9f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        RevolutionaryMoodGrid()
    }
}

@Composable
private fun RevolutionaryFeatureCard(icon: String, title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = icon, fontSize = 32.sp, modifier = Modifier.padding(end = 16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
            }
        }
    }
}

@Composable
private fun RevolutionaryPermissionCard(icon: String, title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3E).copy(alpha = 0.8f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = icon, fontSize = 28.sp, modifier = Modifier.padding(end = 16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
            }
        }
    }
}

@Composable
private fun RevolutionaryMoodGrid() {
    val moods = listOf(
        "😊" to "Happy", "😌" to "Calm", "😰" to "Stressed",
        "😢" to "Sad", "😤" to "Angry", "😐" to "Neutral"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        moods.take(3).forEach { (emoji, mood) -> RevolutionaryMoodCard(emoji, mood) }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        moods.drop(3).forEach { (emoji, mood) -> RevolutionaryMoodCard(emoji, mood) }
    }
}

@Composable
private fun RevolutionaryMoodCard(emoji: String, mood: String) {
    var isSelected by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.size(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFF6B9D).copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = { isSelected = !isSelected }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = emoji, fontSize = 28.sp)
            Text(
                text = mood,
                style = MaterialTheme.typography.labelMedium,
                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun RevolutionaryNavigation(
    currentPage: Int,
    totalPages: Int,
    glowAlpha: Float,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentPage > 0) {
            TextButton(
                onClick = onPrevious,
                colors = ButtonDefaults.textButtonColors(contentColor = Color.White.copy(alpha = 0.8f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text("Previous")
            }
        } else {
            Spacer(modifier = Modifier.width(100.dp))
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(totalPages) { index ->
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            if (index == currentPage) Color(0xFF00D4FF) else Color.White.copy(alpha = 0.3f),
                            CircleShape
                        )
                )
            }
        }

        if (currentPage < totalPages - 1) {
            Button(
                onClick = onNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00D4FF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Color(0xFF00D4FF).copy(alpha = glowAlpha)
                )
            ) {
                Text("Next")
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        } else {
            Button(
                onClick = onNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B9D),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Color(0xFFFF6B9D).copy(alpha = glowAlpha)
                )
            ) {
                Icon(Icons.Default.RocketLaunch, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Get Started")
            }
        }
    }
}
