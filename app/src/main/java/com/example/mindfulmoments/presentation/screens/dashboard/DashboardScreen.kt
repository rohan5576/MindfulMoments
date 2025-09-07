package com.example.mindfulmoments.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mindfulmoments.presentation.components.*
import com.example.mindfulmoments.presentation.theme.*

@Composable
fun DashboardScreen(
    onNavigateBack: () -> Unit,
    onNavigateToMoodTracking: () -> Unit,
    onNavigateToMeditation: () -> Unit,
    onNavigateToBreathing: () -> Unit
) {
    var currentTime by remember { mutableStateOf("") }
    
    // Update time every minute
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
            kotlinx.coroutines.delay(60000) // 1 minute
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                // Welcome Header with Time
                AnimatedWelcomeHeader(
                    title = "Good ${getGreeting()}!",
                    subtitle = "It's $currentTime - time for mindful moments"
                )
            }
            
            item {
                // Quick Stats
                QuickStatsRow()
            }
            
            item {
                // Today's Progress
                TodaysProgressCard()
            }
            
            item {
                // Quick Actions
                QuickActionsSection(
                    onNavigateToMoodTracking = onNavigateToMoodTracking,
                    onNavigateToMeditation = onNavigateToMeditation,
                    onNavigateToBreathing = onNavigateToBreathing
                )
            }
            
            item {
                // Recent Activities
                RecentActivitiesCard()
            }
            
            item {
                // Wellness Tips
                WellnessTipsCard()
            }
            
            item {
                // Upcoming Reminders
                UpcomingRemindersCard()
            }
        }
    }
}

@Composable
private fun QuickStatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        QuickStatCard(
            title = "Mood",
            value = "8.5",
            unit = "/10",
            icon = Icons.Default.Psychology,
            modifier = Modifier.weight(1f)
        )
        QuickStatCard(
            title = "Streak",
            value = "7",
            unit = " days",
            icon = Icons.Default.LocalFireDepartment,
            modifier = Modifier.weight(1f)
        )
        QuickStatCard(
            title = "Minutes",
            value = "45",
            unit = " today",
            icon = Icons.Default.Timer,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun QuickStatCard(
    title: String,
    value: String,
    unit: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = unit,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun TodaysProgressCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today's Progress",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            
            AnimatedProgressRing(
                progress = 0.65f,
                size = 100,
                strokeWidth = 10
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "65% Complete",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "2 of 3 daily goals achieved",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun QuickActionsSection(
    onNavigateToMoodTracking: () -> Unit,
    onNavigateToMeditation: () -> Unit,
    onNavigateToBreathing: () -> Unit
) {
    Column {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                QuickActionCard(
                    title = "Track Mood",
                    icon = Icons.Default.Psychology,
                    onClick = onNavigateToMoodTracking
                )
            }
            item {
                QuickActionCard(
                    title = "Meditate",
                    icon = Icons.Default.SelfImprovement,
                    onClick = onNavigateToMeditation
                )
            }
            item {
                QuickActionCard(
                    title = "Breathe",
                    icon = Icons.Default.Air,
                    onClick = onNavigateToBreathing
                )
            }
        }
    }
}

@Composable
private fun QuickActionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.width(120.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun RecentActivitiesCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Recent Activities",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            val activities = listOf(
                "Morning meditation - 15 min" to "2 hours ago",
                "Mood check-in - Happy (8/10)" to "4 hours ago",
                "Breathing exercise - 5 min" to "6 hours ago"
            )
            
            activities.forEach { (activity, time) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            "Completed",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = activity,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun WellnessTipsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Lightbulb,
                    "Tip",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Wellness Tip of the Day",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Take 3 deep breaths before starting any new task. This simple practice helps center your mind and improves focus throughout the day.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun UpcomingRemindersCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Upcoming Reminders",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            val reminders = listOf(
                "Evening meditation" to "8:00 PM",
                "Mood check-in" to "9:00 PM",
                "Sleep preparation" to "10:00 PM"
            )
            
            reminders.forEach { (reminder, time) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Schedule,
                            "Reminder",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = reminder,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

private fun getGreeting(): String {
    val hour = java.time.LocalTime.now().hour
    return when {
        hour < 12 -> "morning"
        hour < 17 -> "afternoon"
        else -> "evening"
    }
}
