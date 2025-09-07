package com.example.mindfulmoments.presentation.screens.breathing

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.*
import com.example.mindfulmoments.data.repository.AppDataManager
import com.example.mindfulmoments.data.repository.BreathingSessionData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathingScreen(
    onNavigateBack: () -> Unit
) {
    var selectedExercise by remember { mutableStateOf<BreathingExercise?>(null) }
    var isBreathing by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableStateOf(BreathingPhase.INHALE) }
    var timeRemaining by remember { mutableStateOf(0) }
    var totalTime by remember { mutableStateOf(0) }
    
    val coroutineScope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current
    val appDataManager = remember { AppDataManager(context) }
    
    // Real breathing data from AppDataManager
    val breathingData by appDataManager.breathingData.collectAsState()
    
    // Load real breathing data
    LaunchedEffect(Unit) {
        try {
            appDataManager.refreshAllData()
        } catch (e: Exception) {
            Log.e("BreathingScreen", "Error refreshing data", e)
        }
    }
    
    // Advanced Physics-Based Animations
    val infiniteTransition = rememberInfiniteTransition(label = "advanced_breathing")
    
    // Floating particles with physics simulation
    val particleOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f))
        ),
        label = "particles"
    )
    
    // Breathing circle with advanced physics
    val breathingScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing_scale"
    )
    
    // Breathing glow with advanced easing
    val breathingGlow by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing_glow"
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
    
    // Advanced particle system
    val particles = remember {
        List(16) { index ->
            Particle(
                id = index,
                initialX = (index * 26f + 42f) % 360f,
                initialY = (index * 21f + 85f) % 480f,
                speed = 0.4f + (index % 4) * 0.2f,
                size = (2 + index % 3).dp,
                color = when (index % 5) {
                    0 -> Color(0xFF4ECDC4)
                    1 -> Color(0xFF8B5CF6)
                    2 -> Color(0xFFFF6B9D)
                    3 -> Color(0xFF96CEB4)
                    else -> Color(0xFFFFD93D)
                }
            )
        }
    }
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Revolutionary Multi-Layer Background System
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0A0A0F),
                            Color(0xFF1A1A2E),
                            Color(0xFF16213E),
                            Color(0xFF0F3460),
                            Color(0xFF0A0A0F)
                        )
                    )
                )
        )
        
        // Advanced Animated Gradient Overlay with Physics
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4ECDC4).copy(alpha = 0.12f),
                            Color(0xFF8B5CF6).copy(alpha = 0.08f),
                            Color(0xFFFF6B9D).copy(alpha = 0.06f),
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
                    animation = tween(3000 + particle.id * 150, easing = CubicBezierEasing(0.68f, -0.55f, 0.265f, 1.55f)),
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
                        particle.color.copy(alpha = 0.4f),
                        CircleShape
                    )
                    .blur(radius = 2.dp)
                    .graphicsLayer(
                        alpha = 0.8f,
                        scaleX = particleScale,
                        scaleY = particleScale,
                        rotationZ = particleOffset * 360f + particle.id * 30f,
                        // Advanced 3D transformation
                        translationX = sin(chaosFactor * PI.toFloat() + particle.id) * 8f,
                        translationY = cos(chaosFactor * PI.toFloat() + particle.id) * 8f
                    )
                    .zIndex(1f)
            )
            
            // Glow effect for particles
            Box(
                modifier = Modifier
                    .offset(x = animatedX.dp, y = animatedY.dp)
                    .size(particle.size * particleScale * 2f)
                    .background(
                        particle.color.copy(alpha = 0.1f),
                        CircleShape
                    )
                    .blur(radius = 6.dp)
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
                            Color.White.copy(alpha = 0.02f),
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
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
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
            // Premium Header
            item {
                PremiumBreathingHeader(onNavigateBack = onNavigateBack)
            }
            
            // Active Breathing Exercise
            if (isBreathing && selectedExercise != null) {
                item {
                    PremiumActiveBreathing(
                        exercise = selectedExercise!!,
                        currentPhase = currentPhase,
                        timeRemaining = timeRemaining,
                        totalTime = totalTime,
                        breathingScale = breathingScale,
                        breathingGlow = breathingGlow,
                        onStop = {
                            isBreathing = false
                            selectedExercise = null
                        }
                    )
                }
            }
            
            // Breathing Exercises Grid
            item {
                PremiumBreathingExercises(
                    exercises = breathingExercises,
                    onExerciseSelected = { exercise ->
                        selectedExercise = exercise
                        totalTime = exercise.totalDuration
                        timeRemaining = exercise.totalDuration
                        isBreathing = true
                        coroutineScope.launch {
                            startBreathingCycle(exercise) { phase ->
                                currentPhase = phase
                            }
                        }
                    }
                )
            }
            
            // Breathing Tips
            item {
                PremiumBreathingTips()
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun PremiumBreathingHeader(onNavigateBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.3f)
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
                            Color(0xFF4ECDC4).copy(alpha = 0.9f),
                            Color(0xFF6EDDD4).copy(alpha = 0.8f),
                            Color(0xFF8B5CF6).copy(alpha = 0.7f)
                        )
                    )
                )
                .padding(28.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            Color.White.copy(alpha = 0.2f),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.width(20.dp))
                
                Column {
                    Text(
                        text = "Mindful Breathing",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Find calm through conscious breathing",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PremiumActiveBreathing(
    exercise: BreathingExercise,
    currentPhase: BreathingPhase,
    timeRemaining: Int,
    totalTime: Int,
    breathingScale: Float,
    breathingGlow: Float,
    onStop: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.4f)
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
                            Color(0xFF4ECDC4).copy(alpha = 0.9f),
                            Color(0xFF6EDDD4).copy(alpha = 0.8f)
                        )
                    )
                )
                .padding(28.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Breathing Circle
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .shadow(
                            elevation = 30.dp,
                            shape = CircleShape,
                            ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.5f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                CircleShape
                            )
                            .graphicsLayer(
                                scaleX = breathingScale,
                                scaleY = breathingScale
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = getBreathingEmoji(currentPhase),
                            fontSize = 64.sp
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Phase Text
                Text(
                    text = getBreathingPhaseText(currentPhase),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Timer
                Text(
                    text = "${timeRemaining}s",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 48.sp,
                        lineHeight = 56.sp
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Progress Bar
                LinearProgressIndicator(
                    progress = (totalTime - timeRemaining).toFloat() / totalTime.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Stop Button
                Button(
                    onClick = onStop,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.2f)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Stop Session",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun PremiumBreathingExercises(
    exercises: List<BreathingExercise>,
    onExerciseSelected: (BreathingExercise) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(24.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2A2A3E).copy(alpha = 0.95f),
                            Color(0xFF3A3A4E).copy(alpha = 0.9f)
                        )
                    )
                )
                .padding(28.dp)
        ) {
            Column {
                Text(
                    text = "Breathing Exercises",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                LazyVerticalGrid(
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(240.dp)
                ) {
                    items(exercises.size) { index ->
                        val exercise = exercises[index]
                        PremiumExerciseCard(
                            exercise = exercise,
                            onClick = { onExerciseSelected(exercise) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumExerciseCard(
    exercise: BreathingExercise,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = exercise.color.copy(alpha = 0.3f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            exercise.color.copy(alpha = 0.8f),
                            exercise.color.copy(alpha = 0.6f)
                        )
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = exercise.emoji,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = exercise.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${exercise.totalDuration}s",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
private fun PremiumBreathingTips() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(24.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2A2A3E).copy(alpha = 0.95f),
                            Color(0xFF3A3A4E).copy(alpha = 0.9f)
                        )
                    )
                )
                .padding(28.dp)
        ) {
            Column {
                Text(
                    text = "Breathing Tips",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                val tips = listOf(
                    "Find a comfortable position",
                    "Focus on your breath",
                    "Don't force your breathing",
                    "Practice regularly for best results"
                )
                
                tips.forEach { tip ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xFF4ECDC4),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = tip,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }
    }
}

// Data Models
data class BreathingExercise(
    val id: String,
    val title: String,
    val description: String,
    val emoji: String,
    val color: Color,
    val inhaleTime: Int,
    val holdTime: Int,
    val exhaleTime: Int,
    val restTime: Int
) {
    val totalDuration: Int = inhaleTime + holdTime + exhaleTime + restTime
}

enum class BreathingPhase {
    INHALE, HOLD, EXHALE, REST
}

// Real Breathing Exercises
val breathingExercises = listOf(
    BreathingExercise(
        id = "1",
        title = "4-7-8 Breathing",
        description = "Calming technique for stress relief",
        emoji = "😌",
        color = Color(0xFF4ECDC4),
        inhaleTime = 4,
        holdTime = 7,
        exhaleTime = 8,
        restTime = 2
    ),
    BreathingExercise(
        id = "2",
        title = "Box Breathing",
        description = "Equal intervals for focus",
        emoji = "📦",
        color = Color(0xFF8B5CF6),
        inhaleTime = 4,
        holdTime = 4,
        exhaleTime = 4,
        restTime = 4
    ),
    BreathingExercise(
        id = "3",
        title = "Deep Breathing",
        description = "Simple relaxation technique",
        emoji = "💨",
        color = Color(0xFFFF6B9D),
        inhaleTime = 4,
        holdTime = 2,
        exhaleTime = 6,
        restTime = 2
    ),
    BreathingExercise(
        id = "4",
        title = "Alternate Nostril",
        description = "Balancing breathing pattern",
        emoji = "👃",
        color = Color(0xFF96CEB4),
        inhaleTime = 4,
        holdTime = 4,
        exhaleTime = 4,
        restTime = 2
    )
)

// Helper Functions
private fun getBreathingEmoji(phase: BreathingPhase): String {
    return when (phase) {
        BreathingPhase.INHALE -> "🫁"
        BreathingPhase.HOLD -> "⏸️"
        BreathingPhase.EXHALE -> "💨"
        BreathingPhase.REST -> "😌"
    }
}

private fun getBreathingPhaseText(phase: BreathingPhase): String {
    return when (phase) {
        BreathingPhase.INHALE -> "Inhale"
        BreathingPhase.HOLD -> "Hold"
        BreathingPhase.EXHALE -> "Exhale"
        BreathingPhase.REST -> "Rest"
    }
}

private suspend fun startBreathingCycle(
    exercise: BreathingExercise,
    onPhaseChange: (BreathingPhase) -> Unit
) {
    while (true) {
        // Inhale
        onPhaseChange(BreathingPhase.INHALE)
        delay(exercise.inhaleTime * 1000L)
        
        // Hold
        onPhaseChange(BreathingPhase.HOLD)
        delay(exercise.holdTime * 1000L)
        
        // Exhale
        onPhaseChange(BreathingPhase.EXHALE)
        delay(exercise.exhaleTime * 1000L)
        
        // Rest
        onPhaseChange(BreathingPhase.REST)
        delay(exercise.restTime * 1000L)
    }
}

data class Particle(
    val id: Int,
    val initialX: Float,
    val initialY: Float,
    val speed: Float,
    val size: androidx.compose.ui.unit.Dp,
    val color: Color
)
