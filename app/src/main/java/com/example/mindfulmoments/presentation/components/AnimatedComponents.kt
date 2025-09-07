package com.example.mindfulmoments.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mindfulmoments.presentation.theme.*

@Composable
fun AnimatedWelcomeHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun AnimatedMoodCard(
    mood: String,
    intensity: Float,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(getMoodColor(mood).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = mood.first().uppercase(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = getMoodColor(mood),
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = mood.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Intensity: ${(intensity * 10).toInt()}/10",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun AnimatedBreathingCircle(
    phase: BreathingPhase,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(getBreathingColor(phase).copy(alpha = 0.3f))
        )
        
        if (isActive) {
            Text(
                text = when (phase) {
                    BreathingPhase.INHALE -> "Inhale"
                    BreathingPhase.HOLD -> "Hold"
                    BreathingPhase.EXHALE -> "Exhale"
                    BreathingPhase.REST -> "Rest"
                },
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 220.dp)
            )
        }
    }
}

@Composable
fun AnimatedFeatureCard(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun AnimatedProgressRing(
    progress: Float,
    size: Int = 120,
    strokeWidth: Int = 8,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AnimatedNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Dashboard,
                    contentDescription = "Dashboard"
                )
            },
            label = {
                Text("Dashboard")
            },
            selected = currentRoute == "dashboard",
            onClick = { onNavigate("dashboard") }
        )
        
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Psychology,
                    contentDescription = "Mood"
                )
            },
            label = {
                Text("Mood")
            },
            selected = currentRoute == "mood",
            onClick = { onNavigate("mood") }
        )
        
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.SelfImprovement,
                    contentDescription = "Meditation"
                )
            },
            label = {
                Text("Meditation")
            },
            selected = currentRoute == "meditation",
            onClick = { onNavigate("meditation") }
        )
        
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Air,
                    contentDescription = "Breathing"
                )
            },
            label = {
                Text("Breathing")
            },
            selected = currentRoute == "breathing",
            onClick = { onNavigate("breathing") }
        )
        
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Analytics,
                    contentDescription = "Insights"
                )
            },
            label = {
                Text("Insights")
            },
            selected = currentRoute == "insights",
            onClick = { onNavigate("insights") }
        )
    }
}

// Helper functions
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

fun getBreathingColor(phase: BreathingPhase): Color {
    return when (phase) {
        BreathingPhase.INHALE -> Color(0xFF4CAF50)
        BreathingPhase.HOLD -> Color(0xFFFF9800)
        BreathingPhase.EXHALE -> Color(0xFF2196F3)
        BreathingPhase.REST -> Color(0xFF9C27B0)
    }
}
