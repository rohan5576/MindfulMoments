package com.example.mindfulmoments.presentation.screens.main

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import kotlin.math.*
import kotlin.random.Random
import kotlinx.coroutines.delay
import com.example.mindfulmoments.data.repository.AppDataManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDashboardScreen(
    onNavigateToMoodTracking: () -> Unit = {},
    onNavigateToMeditation: () -> Unit = {},
    onNavigateToBreathing: () -> Unit = {},
    onNavigateToInsights: () -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel = remember { MainDashboardViewModel(context) }
    val uiState by viewModel.uiState.collectAsState()
    
    // AppDataManager for comprehensive data
    val appDataManager = remember { AppDataManager(context) }
    val dashboardData by appDataManager.dashboardData.collectAsState()
    
    Log.d("MainDashboardScreen", "Rendering MainDashboardScreen")
    
    LaunchedEffect(Unit) {
        Log.d("MainDashboardScreen", "MainDashboardScreen LaunchedEffect triggered")
        // Refresh all data when screen loads
        viewModel.refreshData()
    }
    
    // Advanced Physics-Based Animations
    val infiniteTransition = rememberInfiniteTransition(label = "advanced_dashboard")
    
    // Floating particles with physics simulation
    val particleOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f))
        ),
        label = "particles"
    )
    
    // Breathing glow with advanced easing
    val breathingGlow by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing_glow"
    )
    
    // Mood glow animation from Track mood screen
    val moodGlow by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mood_glow"
    )
    
    // Magnetic attraction effect
    val magneticField by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = CubicBezierEasing(0.68f, -0.55f, 0.265f, 1.55f))
        ),
        label = "magnetic_field"
    )
    
    // Fluid dynamics simulation
    val fluidFlow by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = CubicBezierEasing(0.23f, 1f, 0.32f, 1f))
        ),
        label = "fluid_flow"
    )
    
    // Chaos theory randomness
    val chaosFactor by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f))
        ),
        label = "chaos_factor"
    )
    
    // Dynamic wisdom quote selection
    var currentWisdomIndex by remember { mutableStateOf(Random.nextInt(1, 201)) }
    
    // Progress data from ViewModel
    val moodProgress = uiState.moodProgress
    val meditationProgress = uiState.meditationProgress
    val breathingProgress = uiState.breathingProgress
    val totalSessions = uiState.totalSessions
    val totalMinutes = uiState.totalMinutes
    val currentStreak = uiState.currentStreak
    val weeklyGoal = uiState.weeklyGoal
    val weeklyProgress = uiState.weeklyProgress
    val totalDays = uiState.totalDays
    val userName = uiState.userName
    
    // Progress data is now loaded automatically by the ViewModel
    
    // Change wisdom quote every 10 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(10000)
            currentWisdomIndex = Random.nextInt(1, 201)
        }
    }
    
    // Advanced particle system
    val particles = remember {
        List(12) { index ->
            Particle(
                id = index,
                initialX = (index * 30f + 50f) % 400f,
                initialY = (index * 25f + 100f) % 600f,
                speed = 0.5f + (index % 3) * 0.3f,
                size = (3 + index % 4).dp,
                color = when (index % 4) {
                    0 -> Color(0xFF4ECDC4)
                    1 -> Color(0xFF8B5CF6)
                    2 -> Color(0xFFFF6B9D)
                    else -> Color(0xFF96CEB4)
                }
            )
        }
    }
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Revolutionary Multi-Layer Background System with Brighter Colors
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A1A3E), // Brighter dark blue
                            Color(0xFF2A2A5E), // Brighter blue
                            Color(0xFF3A3A7E), // Brighter purple-blue
                            Color(0xFF4A4A9E), // Brighter purple
                            Color(0xFF1A1A3E)  // Brighter dark blue
                        )
                    )
                )
        )
        
        // Advanced Animated Gradient Overlay with Physics and Brighter Colors
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4ECDC4).copy(alpha = 0.18f), // Brighter teal
                            Color(0xFF8B5CF6).copy(alpha = 0.15f), // Brighter purple
                            Color(0xFFFF6B9D).copy(alpha = 0.12f), // Brighter pink
                            Color(0xFF96CEB4).copy(alpha = 0.10f), // Brighter green
                            Color.Transparent
                        ),
                        center = androidx.compose.ui.geometry.Offset(
                            x = 0.5f + sin(particleOffset * 2 * PI.toFloat()) * 0.3f,
                            y = 0.4f + cos(particleOffset * 2 * PI.toFloat()) * 0.3f
                        ),
                        radius = 800f + sin(fluidFlow * 2 * PI.toFloat()) * 200f
                    )
                )
        )
        
        // Advanced Particle System with Physics
        particles.forEach { particle ->
            val animatedX by animateFloatAsState(
                targetValue = particle.initialX + sin(particleOffset * 2 * PI.toFloat() + particle.id) * 200f * particle.speed,
                animationSpec = tween(20000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f)),
                label = "particle_x_${particle.id}"
            )
            
            val animatedY by animateFloatAsState(
                targetValue = particle.initialY + cos(particleOffset * 2 * PI.toFloat() + particle.id) * 150f * particle.speed,
                animationSpec = tween(20000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f)),
                label = "particle_y_${particle.id}"
            )
            
            val particleScale by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1.5f,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000 + particle.id * 200, easing = CubicBezierEasing(0.68f, -0.55f, 0.265f, 1.55f)),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "particle_scale_${particle.id}"
            )
            
            // Advanced glassmorphism particle with blur
            Box(
                modifier = Modifier
                    .offset(x = animatedX.dp, y = animatedY.dp)
                    .size(particle.size * particleScale)
                    .background(
                        particle.color.copy(alpha = 0.6f),
                        CircleShape
                    )
                    .blur(radius = 2.dp)
                    .graphicsLayer(
                        alpha = 0.8f,
                        scaleX = particleScale,
                        scaleY = particleScale,
                        rotationZ = particleOffset * 360f + particle.id * 30f,
                        // Advanced 3D transformation
                        translationX = sin(chaosFactor * PI.toFloat() + particle.id) * 10f,
                        translationY = cos(chaosFactor * PI.toFloat() + particle.id) * 10f
                    )
                    .zIndex(1f)
            )
            
            // Glow effect for particles
            Box(
                modifier = Modifier
                    .offset(x = animatedX.dp, y = animatedY.dp)
                    .size(particle.size * particleScale * 2f)
                    .background(
                        particle.color.copy(alpha = 0.15f),
                        CircleShape
                    )
                    .blur(radius = 8.dp)
                    .graphicsLayer(
                        alpha = 0.4f + breathingGlow * 0.3f,
                        scaleX = particleScale * 0.8f,
                        scaleY = particleScale * 0.8f
                    )
                    .zIndex(0.5f)
            )
        }
        
        // Advanced Glassmorphism Overlay with Blur
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.03f),
                            Color.Transparent
                        ),
                        center = androidx.compose.ui.geometry.Offset(
                            x = 0.3f + sin(fluidFlow * PI.toFloat()) * 0.4f,
                            y = 0.7f + cos(fluidFlow * PI.toFloat()) * 0.3f
                        ),
                        radius = 600f
                    )
                )
                .blur(radius = 1.dp)
        )
        
        // Advanced Content with GPU Acceleration
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 60.dp) // Fixed top padding for status bar
                .padding(bottom = 120.dp)
                .graphicsLayer(
                    // GPU acceleration hints
                    renderEffect = null,
                    alpha = 1f,
                    scaleX = 1f,
                    scaleY = 1f
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Revolutionary Header with Advanced Glassmorphism and Brighter Colors
            item {
                RevolutionaryHeaderSection(
                    breathingGlow = breathingGlow,
                    magneticField = magneticField,
                    moodGlow = moodGlow
                )
            }
            
            // Ultra-Modern Welcome Card with Neumorphism and Brighter Colors
            item {
                UltraModernWelcomeCard(
                    breathingGlow = breathingGlow,
                    fluidFlow = fluidFlow,
                    moodGlow = moodGlow,
                    userName = userName
                )
            }
            
            // Revolutionary Quick Actions with Advanced Effects and Brighter Colors
            item {
                RevolutionaryQuickActionsSection(
                    onNavigateToMoodTracking = onNavigateToMoodTracking,
                    onNavigateToMeditation = onNavigateToMeditation,
                    onNavigateToBreathing = onNavigateToBreathing,
                    onNavigateToInsights = onNavigateToInsights,
                    chaosFactor = chaosFactor,
                    magneticField = magneticField,
                    moodGlow = moodGlow
                )
            }
            
            // Advanced Daily Wisdom with Aurora Effects and Dynamic Quotes
            item {
                AdvancedDailyWisdomCard(
                    fluidFlow = fluidFlow,
                    chaosFactor = chaosFactor,
                    currentWisdomIndex = currentWisdomIndex,
                    moodGlow = moodGlow
                )
            }
            
            // Revolutionary Progress Section with 3D Effects and Real Data
            item {
                RevolutionaryProgressSection(
                    magneticField = magneticField,
                    breathingGlow = breathingGlow,
                    moodProgress = moodProgress,
                    meditationProgress = meditationProgress,
                    breathingProgress = breathingProgress,
                    totalSessions = totalSessions,
                    totalMinutes = totalMinutes,
                    currentStreak = currentStreak,
                    weeklyGoal = weeklyGoal,
                    weeklyProgress = weeklyProgress,
                    moodGlow = moodGlow
                )
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun RevolutionaryHeaderSection(
    breathingGlow: Float,
    magneticField: Float,
    moodGlow: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp) // Increased height to prevent text cutoff
            .shadow(
                elevation = 35.dp,
                shape = RoundedCornerShape(32.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.5f),
                spotColor = Color(0xFF8B5CF6).copy(alpha = 0.7f)
            )
            .graphicsLayer(
                // Advanced 3D transformation
                rotationX = sin(magneticField * 2 * PI.toFloat()) * 5f,
                rotationY = cos(magneticField * 2 * PI.toFloat()) * 5f,
                scaleX = 1f + sin(breathingGlow * PI.toFloat()) * 0.02f,
                scaleY = 1f + cos(breathingGlow * PI.toFloat()) * 0.02f
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF4ECDC4).copy(alpha = 0.98f),
                            Color(0xFF6EDDD4).copy(alpha = 0.95f),
                            Color(0xFF8B5CF6).copy(alpha = 0.92f),
                            Color(0xFFEC4899).copy(alpha = 0.88f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Advanced Animated Logo with Physics and Mood Glow
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.4f),
                                    Color.White.copy(alpha = 0.2f)
                                )
                            ),
                            CircleShape
                        )
                        .graphicsLayer(
                            scaleX = 1f + sin(breathingGlow * PI.toFloat()) * 0.1f,
                            scaleY = 1f + cos(breathingGlow * PI.toFloat()) * 0.1f,
                            rotationZ = magneticField * 360f,
                            // Add mood glow effect
                            alpha = 0.9f + moodGlow * 0.1f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🧘",
                        fontSize = 48.sp,
                        modifier = Modifier.graphicsLayer(
                            alpha = 0.95f + breathingGlow * 0.05f,
                            // Add mood glow effect
                            scaleX = 1f + moodGlow * 0.05f,
                            scaleY = 1f + moodGlow * 0.05f
                        )
                    )
                }
                
                Spacer(modifier = Modifier.width(24.dp))
                
                Column(
                    modifier = Modifier.weight(1f) // Take remaining space
                ) {
                    Text(
                        text = "Mindful Moments",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.graphicsLayer(
                            alpha = 0.98f + breathingGlow * 0.02f,
                            // Add mood glow effect
                            scaleX = 1f + moodGlow * 0.02f,
                            scaleY = 1f + moodGlow * 0.02f
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Your journey to inner peace begins here",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.95f),
                        modifier = Modifier.graphicsLayer(
                            alpha = 0.85f + breathingGlow * 0.15f,
                            // Add mood glow effect
                            scaleX = 1f + moodGlow * 0.01f
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun UltraModernWelcomeCard(
    breathingGlow: Float,
    fluidFlow: Float,
    moodGlow: Float,
    userName: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(
                elevation = 30.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF2A2A3E).copy(alpha = 0.6f),
                spotColor = Color(0xFF4ECDC4).copy(alpha = 0.5f)
            )
            .graphicsLayer(
                // Advanced neumorphism effect
                scaleX = 1f + sin(fluidFlow * PI.toFloat()) * 0.01f,
                scaleY = 1f + cos(fluidFlow * PI.toFloat()) * 0.01f,
                rotationZ = sin(fluidFlow * 2 * PI.toFloat()) * 2f
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2A2A3E).copy(alpha = 0.99f),
                            Color(0xFF3A3A4E).copy(alpha = 0.96f),
                            Color(0xFF4A4A5E).copy(alpha = 0.93f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (userName.isNotBlank()) "Welcome back, $userName!" else "Welcome back!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer(
                        alpha = 0.95f + breathingGlow * 0.05f,
                        // Add mood glow effect
                        scaleX = 1f + moodGlow * 0.02f
                    )
                )
                
                // Row layout to place breathing circle and text side by side
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Advanced Breathing Circle with Physics and Mood Glow
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF4ECDC4).copy(alpha = 0.9f),
                                        Color(0xFF6EDDD4).copy(alpha = 0.7f),
                                        Color(0xFF8B5CF6).copy(alpha = 0.5f)
                                    )
                                ),
                                CircleShape
                            )
                            .graphicsLayer(
                                scaleX = 1f + sin(breathingGlow * PI.toFloat()) * 0.2f,
                                scaleY = 1f + sin(breathingGlow * PI.toFloat()) * 0.2f,
                                rotationZ = fluidFlow * 180f,
                                // Add mood glow effect
                                alpha = 0.9f + moodGlow * 0.1f
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "💨",
                            fontSize = 32.sp,
                            modifier = Modifier.graphicsLayer(
                                alpha = 0.85f + breathingGlow * 0.15f,
                                // Add mood glow effect
                                scaleX = 1f + moodGlow * 0.05f,
                                scaleY = 1f + moodGlow * 0.05f
                            )
                        )
                    }
                    
                    // Breathing text placed beside the icon
                    Text(
                        text = "Take a moment to breathe and center yourself",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                            .graphicsLayer(
                                alpha = 0.8f + breathingGlow * 0.2f,
                                // Add mood glow effect
                                scaleX = 1f + moodGlow * 0.01f
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun RevolutionaryQuickActionsSection(
    onNavigateToMoodTracking: () -> Unit,
    onNavigateToMeditation: () -> Unit,
    onNavigateToBreathing: () -> Unit,
    onNavigateToInsights: () -> Unit,
    chaosFactor: Float,
    magneticField: Float,
    moodGlow: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 25.dp, // Increased elevation
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF2A2A3E).copy(alpha = 0.5f) // Brighter ambient
            )
            .graphicsLayer(
                // Advanced magnetic attraction effect
                scaleX = 1f + sin(magneticField * PI.toFloat()) * 0.005f,
                scaleY = 1f + cos(magneticField * PI.toFloat()) * 0.005f
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2A2A3E).copy(alpha = 0.99f), // Brighter dark
                            Color(0xFF3A3A4E).copy(alpha = 0.96f)  // Brighter blue
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                        .graphicsLayer(
                            // Add mood glow effect
                            scaleX = 1f + moodGlow * 0.02f
                        )
                )
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(320.dp)
                ) {
                    // Track Mood
                    item {
                        RevolutionaryActionCard(
                            action = QuickAction(
                                id = 0,
                                title = "Track Mood",
                                icon = Icons.Default.Psychology,
                                accentColor = Color(0xFF4ECDC4),
                                onClick = { }
                            ),
                            onClick = onNavigateToMoodTracking,
                            chaosFactor = chaosFactor,
                            magneticField = magneticField,
                            moodGlow = moodGlow
                        )
                    }
                    
                    // Meditate
                    item {
                        RevolutionaryActionCard(
                            action = QuickAction(
                                id = 1,
                                title = "Meditate",
                                icon = Icons.Default.SelfImprovement,
                                accentColor = Color(0xFF8B5CF6),
                                onClick = { }
                            ),
                            onClick = onNavigateToMeditation,
                            chaosFactor = chaosFactor,
                            magneticField = magneticField,
                            moodGlow = moodGlow
                        )
                    }
                    
                    // Breathe
                    item {
                        RevolutionaryActionCard(
                            action = QuickAction(
                                id = 2,
                                title = "Breathe",
                                icon = Icons.Default.Air,
                                accentColor = Color(0xFFFF6B9D),
                                onClick = { }
                            ),
                            onClick = onNavigateToBreathing,
                            chaosFactor = chaosFactor,
                            magneticField = magneticField,
                            moodGlow = moodGlow
                        )
                    }
                    
                    // Insights
                    item {
                        RevolutionaryActionCard(
                            action = QuickAction(
                                id = 3,
                                title = "Insights",
                                icon = Icons.Default.Analytics,
                                accentColor = Color(0xFF96CEB4),
                                onClick = { }
                            ),
                            onClick = onNavigateToInsights,
                            chaosFactor = chaosFactor,
                            magneticField = magneticField,
                            moodGlow = moodGlow
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RevolutionaryActionCard(
    action: QuickAction,
    onClick: () -> Unit,
    chaosFactor: Float,
    magneticField: Float,
    moodGlow: Float
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .shadow(
                elevation = 20.dp, // Increased elevation
                shape = RoundedCornerShape(24.dp),
                ambientColor = action.accentColor.copy(alpha = 0.4f) // Brighter ambient
            )
            .graphicsLayer(
                // Advanced chaos theory effects
                scaleX = 1f + sin(chaosFactor * PI.toFloat() + action.id * 0.5f) * 0.03f,
                scaleY = 1f + cos(chaosFactor * PI.toFloat() + action.id * 0.5f) * 0.03f,
                rotationZ = sin(magneticField * PI.toFloat() + action.id * 0.3f) * 3f,
                // Add mood glow effect
                alpha = 0.95f + moodGlow * 0.05f
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            action.accentColor.copy(alpha = 0.95f), // Brighter colors
                            action.accentColor.copy(alpha = 0.8f),  // Brighter colors
                            action.accentColor.copy(alpha = 0.6f)   // Brighter colors
                        )
                    )
                )
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = action.icon,
                    contentDescription = action.title,
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .graphicsLayer(
                            scaleX = 1f + sin(chaosFactor * PI.toFloat()) * 0.1f,
                            scaleY = 1f + sin(chaosFactor * PI.toFloat()) * 0.1f,
                            // Add mood glow effect
                            alpha = 0.95f + moodGlow * 0.05f
                        )
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = action.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun AdvancedDailyWisdomCard(
    fluidFlow: Float,
    chaosFactor: Float,
    currentWisdomIndex: Int,
    moodGlow: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 28.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF8B5CF6).copy(alpha = 0.5f)
            )
            .graphicsLayer(
                // Advanced fluid dynamics effect
                scaleX = 1f + sin(fluidFlow * PI.toFloat()) * 0.01f,
                scaleY = 1f + cos(fluidFlow * PI.toFloat()) * 0.01f,
                rotationZ = sin(chaosFactor * PI.toFloat()) * 1f
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF8B5CF6).copy(alpha = 0.98f),
                            Color(0xFFEC4899).copy(alpha = 0.95f),
                            Color(0xFFFF6B9D).copy(alpha = 0.92f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🌟",
                    fontSize = 48.sp,
                    modifier = Modifier.graphicsLayer(
                        scaleX = 1f + sin(chaosFactor * PI.toFloat()) * 0.1f,
                        scaleY = 1f + sin(chaosFactor * PI.toFloat()) * 0.1f,
                        rotationZ = fluidFlow * 360f,
                        // Add mood glow effect
                        alpha = 0.95f + moodGlow * 0.05f
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Daily Wisdom",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Dynamic wisdom quote from wisdom strings file
                val context = LocalContext.current
                val wisdomResourceName = "wisdom_$currentWisdomIndex"
                val wisdomText = try {
                    val resourceId = context.resources.getIdentifier(wisdomResourceName, "string", context.packageName)
                    if (resourceId != 0) {
                        context.getString(resourceId)
                    } else {
                        // Fallback to a default quote if resource not found
                        "Peace comes from within. Do not seek it without."
                    }
                } catch (e: Exception) {
                    "Peace comes from within. Do not seek it without."
                }
                
                Text(
                    text = wisdomText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.95f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.graphicsLayer(
                        alpha = 0.9f + fluidFlow * 0.1f,
                        // Add mood glow effect
                        scaleX = 1f + moodGlow * 0.01f
                    )
                )
                
                // Add a subtle indicator that quotes change automatically
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "✨ New wisdom every 10 seconds",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun RevolutionaryProgressSection(
    magneticField: Float,
    breathingGlow: Float,
    moodProgress: Int,
    meditationProgress: Int,
    breathingProgress: Int,
    totalSessions: Int,
    totalMinutes: Int,
    currentStreak: Int,
    weeklyGoal: Int,
    weeklyProgress: Int,
    moodGlow: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 22.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF2A2A3E).copy(alpha = 0.5f)
            )
            .graphicsLayer(
                // Advanced magnetic field effect
                scaleX = 1f + sin(magneticField * PI.toFloat()) * 0.005f,
                scaleY = 1f + cos(magneticField * PI.toFloat()) * 0.005f
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2A2A3E).copy(alpha = 0.99f),
                            Color(0xFF3A3A4E).copy(alpha = 0.96f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column {
                Text(
                    text = "Your Progress",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                        .graphicsLayer(
                            // Add mood glow effect
                            scaleX = 1f + moodGlow * 0.02f
                        )
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RevolutionaryProgressIndicator(
                        label = "Mood",
                        value = "$moodProgress%",
                        color = Color(0xFF4ECDC4),
                        magneticField = magneticField,
                        moodGlow = moodGlow
                    )
                    RevolutionaryProgressIndicator(
                        label = "Meditation",
                        value = "$meditationProgress%",
                        color = Color(0xFF8B5CF6),
                        magneticField = magneticField,
                        moodGlow = moodGlow
                    )
                    RevolutionaryProgressIndicator(
                        label = "Breathing",
                        value = "$breathingProgress%",
                        color = Color(0xFFFF6B9D),
                        magneticField = magneticField,
                        moodGlow = moodGlow
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Additional progress metrics with real data
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RevolutionaryProgressIndicator(
                        label = "Sessions",
                        value = "$totalSessions",
                        color = Color(0xFF96CEB4),
                        magneticField = magneticField,
                        moodGlow = moodGlow
                    )
                    RevolutionaryProgressIndicator(
                        label = "Minutes",
                        value = "$totalMinutes",
                        color = Color(0xFFFFD93D),
                        magneticField = magneticField,
                        moodGlow = moodGlow
                    )
                    RevolutionaryProgressIndicator(
                        label = "Streak",
                        value = "${currentStreak}d",
                        color = Color(0xFFEC4899),
                        magneticField = magneticField,
                        moodGlow = moodGlow
                    )
                }
                
                // Weekly progress indicator
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 15.dp,
                            shape = RoundedCornerShape(20.dp),
                            ambientColor = Color(0xFF8B5CF6).copy(alpha = 0.3f)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF8B5CF6).copy(alpha = 0.2f),
                                        Color(0xFFEC4899).copy(alpha = 0.15f)
                                    )
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Weekly Goal",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "$weeklyProgress/$weeklyGoal sessions",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color(0xFF8B5CF6),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            LinearProgressIndicator(
                                progress = weeklyProgress.toFloat() / weeklyGoal.toFloat(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp),
                                color = Color(0xFF8B5CF6),
                                trackColor = Color.White.copy(alpha = 0.2f)
                            )
                        }
                    }
                }
                
                // Add progress summary text
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Keep up the great work! Your mindfulness journey is progressing beautifully.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.graphicsLayer(
                        alpha = 0.8f + moodGlow * 0.2f
                    )
                )
            }
        }
    }
}

@Composable
private fun RevolutionaryProgressIndicator(
    label: String,
    value: String,
    color: Color,
    magneticField: Float,
    moodGlow: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color.copy(alpha = 0.4f), // Brighter background
                    CircleShape
                )
                .graphicsLayer(
                    scaleX = 1f + sin(magneticField * PI.toFloat()) * 0.05f,
                    scaleY = 1f + cos(magneticField * PI.toFloat()) * 0.05f,
                    rotationZ = magneticField * 180f,
                    // Add mood glow effect
                    alpha = 0.95f + moodGlow * 0.05f
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = color,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.9f), // Brighter text
            modifier = Modifier.graphicsLayer(
                // Add mood glow effect
                scaleX = 1f + moodGlow * 0.01f
            )
        )
    }
}

// Data Models
data class QuickAction(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val accentColor: Color,
    val onClick: () -> Unit
)

data class Particle(
    val id: Int,
    val initialX: Float,
    val initialY: Float,
    val speed: Float,
    val size: androidx.compose.ui.unit.Dp,
    val color: Color
)

// Quick Actions Data with Brighter Colors
val quickActions = listOf(
    QuickAction(
        id = 0,
        title = "Track Mood",
        icon = Icons.Default.Psychology,
        accentColor = Color(0xFF4ECDC4), // Brighter teal
        onClick = { /* Will be passed from parent */ }
    ),
    QuickAction(
        id = 1,
        title = "Meditate",
        icon = Icons.Default.SelfImprovement,
        accentColor = Color(0xFF8B5CF6), // Brighter purple
        onClick = { /* Will be passed from parent */ }
    ),
    QuickAction(
        id = 2,
        title = "Breathe",
        icon = Icons.Default.Air,
        accentColor = Color(0xFFFF6B9D), // Brighter pink
        onClick = { /* Will be passed from parent */ }
    ),
    QuickAction(
        id = 3,
        title = "Insights",
        icon = Icons.Default.Analytics,
        accentColor = Color(0xFF96CEB4), // Brighter green
        onClick = { /* Will be passed from parent */ }
    )
)

