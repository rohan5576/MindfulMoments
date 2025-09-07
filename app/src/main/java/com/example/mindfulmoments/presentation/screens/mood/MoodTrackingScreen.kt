package com.example.mindfulmoments.presentation.screens.mood

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.*
import com.example.mindfulmoments.data.repository.SimpleDataManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodTrackingScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Advanced Physics-Based Animations
    val infiniteTransition = rememberInfiniteTransition(label = "advanced_mood_tracking")

    // Floating particles with physics simulation
    val particleOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f))
        ),
        label = "particles"
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

    // Success animation with physics
    val successAlpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = CubicBezierEasing(0.68f, -0.55f, 0.265f, 1.55f))
        ),
        label = "success_alpha"
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
        List(15) { index ->
            Particle(
                id = index,
                initialX = (index * 25f + 40f) % 350f,
                initialY = (index * 20f + 80f) % 500f,
                speed = 0.4f + (index % 4) * 0.2f,
                size = (2 + index % 3).dp,
                color = when (index % 5) {
                    0 -> Color(0xFFFF6B9D)
                    1 -> Color(0xFF4ECDC4)
                    2 -> Color(0xFF8B5CF6)
                    3 -> Color(0xFF96CEB4)
                    else -> Color(0xFFFFD93D)
                }
            )
        }
    }

    // State variables
    var selectedMood by remember { mutableStateOf<String?>(null) }
    var moodIntensity by remember { mutableStateOf(5f) }
    var moodNote by remember { mutableStateOf("") }
    var showSuccessMessage by remember { mutableStateOf(false) }
    val recentMoods = remember { mutableStateListOf<RecentMoodEntry>() }

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
                            Color(0xFFFF6B9D).copy(alpha = 0.15f),
                            Color(0xFF4ECDC4).copy(alpha = 0.12f),
                            Color(0xFF8B5CF6).copy(alpha = 0.08f),
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
                targetValue = particle.initialX + sin(particleOffset * 2 * PI.toFloat() + particle.id) * 180f * particle.speed,
                animationSpec = tween(20000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f)),
                label = "particle_x_${particle.id}"
            )

            val animatedY by animateFloatAsState(
                targetValue = particle.initialY + cos(particleOffset * 2 * PI.toFloat() + particle.id) * 120f * particle.speed,
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
                        alpha = 0.4f + moodGlow * 0.3f,
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

        // Advanced Content with GPU Acceleration
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
            item { Spacer(modifier = Modifier.height(16.dp)) } // 👈 Top padding

            // Revolutionary Header with Advanced Glassmorphism
            item {
                RevolutionaryMoodHeader(
                    onNavigateBack = onNavigateBack,
                    moodGlow = moodGlow,
                    magneticField = magneticField
                )
            }

            // Ultra-Modern Mood Selection with Physics
            item {
                RevolutionaryMoodSelection(
                    selectedMood = selectedMood,
                    onMoodSelected = { selectedMood = it },
                    chaosFactor = chaosFactor,
                    magneticField = magneticField
                )
            }

            // Advanced Mood Intensity with Neumorphism
            item {
                RevolutionaryMoodIntensity(
                    intensity = moodIntensity,
                    onIntensityChanged = { moodIntensity = it },
                    fluidFlow = fluidFlow
                )
            }

            // Revolutionary Mood Notes with Glassmorphism
            item {
                RevolutionaryMoodNotes(
                    note = moodNote,
                    onNoteChanged = { moodNote = it },
                    breathingGlow = moodGlow
                )
            }

            // Advanced Save Button with Physics
            item {
                RevolutionarySaveButton(
                    onClick = { 
                        val currentMood = selectedMood
                        if (currentMood != null) {
                            // Save mood entry to data manager
                            val dataManager = SimpleDataManager(context)
                            dataManager.addMoodEntry(currentMood, moodIntensity.toInt(), moodNote)
                            
                            // Add to recent moods
                            recentMoods.add(
                                0, // add at top
                                RecentMoodEntry(
                                    emoji = moods.find { it.second == currentMood }?.first ?: "",
                                    mood = currentMood,
                                    time = "Just now",
                                    intensity = moodIntensity.toInt(),
                                    color = moods.find { it.second == currentMood }?.third ?: Color.Gray
                                )
                            )
                            
                            // Show success message
                            showSuccessMessage = true
                            
                            // Reset form after 2 seconds
                            coroutineScope.launch {
                                delay(2000)
                                showSuccessMessage = false
                                selectedMood = null
                                moodIntensity = 5f
                                moodNote = ""
                            }
                        }
                    },
                    selectedMood = selectedMood,
                    moodGlow = moodGlow,
                    chaosFactor = chaosFactor
                )
            }

            // Revolutionary Recent Moods with Advanced Effects
            item {
                RevolutionaryRecentMoods(
                    magneticField = magneticField,
                    fluidFlow = fluidFlow,
                    recentMoods = recentMoods

                )
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }

        // Simple Toast Message
        if (showSuccessMessage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Card(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .graphicsLayer(alpha = successAlpha),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4ECDC4)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "✅ Mood saved successfully!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RevolutionaryMoodHeader(
    onNavigateBack: () -> Unit,
    moodGlow: Float,
    magneticField: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(
                elevation = 30.dp,
                shape = RoundedCornerShape(32.dp),
                ambientColor = Color(0xFFFF6B9D).copy(alpha = 0.4f),
                spotColor = Color(0xFF4ECDC4).copy(alpha = 0.6f)
            )
            .graphicsLayer(
                // Advanced 3D transformation
                rotationX = sin(magneticField * 2 * PI.toFloat()) * 5f,
                rotationY = cos(magneticField * 2 * PI.toFloat()) * 5f,
                scaleX = 1f + sin(moodGlow * PI.toFloat()) * 0.02f,
                scaleY = 1f + cos(moodGlow * PI.toFloat()) * 0.02f
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
                            Color(0xFFFF6B9D).copy(alpha = 0.95f),
                            Color(0xFFFF8FA3).copy(alpha = 0.9f),
                            Color(0xFF4ECDC4).copy(alpha = 0.85f),
                            Color(0xFF8B5CF6).copy(alpha = 0.8f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Advanced Animated Back Button with Physics
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            Color.White.copy(alpha = 0.2f),
                            CircleShape
                        )
                        .graphicsLayer(
                            scaleX = 1f + sin(magneticField * PI.toFloat()) * 0.1f,
                            scaleY = 1f + sin(magneticField * PI.toFloat()) * 0.1f
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Column {
                    Text(
                        text = "Track Your Mood",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.graphicsLayer(
                            alpha = 0.95f + moodGlow * 0.05f
                        )
                    )
                    Text(
                        text = "How are you feeling today?",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.graphicsLayer(
                            alpha = 0.8f + moodGlow * 0.2f
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun RevolutionaryMoodSelection(
    selectedMood: String?,
    onMoodSelected: (String) -> Unit,
    chaosFactor: Float,
    magneticField: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF2A2A3E).copy(alpha = 0.5f)
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
                            Color(0xFF2A2A3E).copy(alpha = 0.98f),
                            Color(0xFF3A3A4E).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column {
                Text(
                    text = "Select Your Mood",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(200.dp)
                ) {
                    items(moods) { mood ->
                        RevolutionaryMoodCard(
                            mood = mood,
                            isSelected = selectedMood == mood.second,
                            onSelect = { onMoodSelected(mood.second) },
                            chaosFactor = chaosFactor,
                            magneticField = magneticField
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RevolutionaryMoodCard(
    mood: Triple<String, String, Color>,
    isSelected: Boolean,
    onSelect: () -> Unit,
    chaosFactor: Float,
    magneticField: Float
) {
    val (emoji, name, color) = mood

    Card(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(3.dp)
            .shadow(
                elevation = if (isSelected) 20.dp else 15.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = color.copy(alpha = if (isSelected) 0.5f else 0.3f)
            )
            .graphicsLayer(
                // Advanced chaos theory effects
                scaleX = 1f + sin(chaosFactor * PI.toFloat()) * 0.03f,
                scaleY = 1f + cos(chaosFactor * PI.toFloat()) * 0.03f,
                rotationZ = sin(magneticField * PI.toFloat()) * 2f
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                color.copy(alpha = 0.9f)
            } else {
                Color(0xFF2A2A3E).copy(alpha = 0.8f)
            }
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isSelected) {
                        Brush.radialGradient(
                            colors = listOf(
                                color.copy(alpha = 0.9f),
                                color.copy(alpha = 0.7f),
                                color.copy(alpha = 0.5f)
                            )
                        )
                    } else {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF2A2A3E).copy(alpha = 0.8f),
                                Color(0xFF3A3A4E).copy(alpha = 0.7f)
                            )
                        )
                    }
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = emoji,
                    fontSize = 32.sp,
                    modifier = Modifier.graphicsLayer(
                        scaleX = 1f + sin(chaosFactor * PI.toFloat()) * 0.1f,
                        scaleY = 1f + sin(chaosFactor * PI.toFloat()) * 0.1f
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) Color.White else Color.White.copy(alpha = 0.8f),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun RevolutionaryMoodIntensity(
    intensity: Float,
    onIntensityChanged: (Float) -> Unit,
    fluidFlow: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 22.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.4f)
            )
            .graphicsLayer(
                // Advanced fluid dynamics effect
                scaleX = 1f + sin(fluidFlow * PI.toFloat()) * 0.01f,
                scaleY = 1f + cos(fluidFlow * PI.toFloat()) * 0.01f
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
                            Color(0xFF2A2A3E).copy(alpha = 0.98f),
                            Color(0xFF3A3A4E).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column {
                Text(
                    text = "Mood Intensity",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "${intensity.toInt()}/10",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF4ECDC4),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Slider(
                    value = intensity,
                    onValueChange = onIntensityChanged,
                    valueRange = 1f..10f,
                    steps = 8,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF4ECDC4),
                        activeTrackColor = Color(0xFF4ECDC4),
                        inactiveTrackColor = Color(0xFF4ECDC4).copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.graphicsLayer(
                        scaleX = 1f + sin(fluidFlow * PI.toFloat()) * 0.005f
                    )
                )
            }
        }
    }
}

@Composable
private fun RevolutionaryMoodNotes(
    note: String,
    onNoteChanged: (String) -> Unit,
    breathingGlow: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF8B5CF6).copy(alpha = 0.4f)
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
                            Color(0xFF2A2A3E).copy(alpha = 0.98f),
                            Color(0xFF3A3A4E).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column {
                Text(
                    text = "Additional Notes",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = note,
                    onValueChange = onNoteChanged,
                    placeholder = {
                        Text(
                            text = "How are you feeling? Any specific thoughts?",
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer(
                            alpha = 0.9f + breathingGlow * 0.1f
                        ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF8B5CF6),
                        unfocusedBorderColor = Color(0xFF8B5CF6).copy(alpha = 0.5f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White.copy(alpha = 0.9f)
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
private fun RevolutionarySaveButton(
    onClick: () -> Unit,
    selectedMood: String?,
    moodGlow: Float,
    chaosFactor: Float
) {
    Card(
        onClick = onClick,
        enabled = selectedMood != null,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.5f)
            )
            .graphicsLayer(
                // Advanced chaos theory effects
                scaleX = 1f + sin(chaosFactor * PI.toFloat()) * 0.02f,
                scaleY = 1f + cos(chaosFactor * PI.toFloat()) * 0.02f
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
                            Color(0xFF4ECDC4).copy(alpha = 0.9f),
                            Color(0xFF6EDDD4).copy(alpha = 0.8f),
                            Color(0xFF8B5CF6).copy(alpha = 0.7f)
                        )
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .graphicsLayer(
                            scaleX = 1f + sin(moodGlow * PI.toFloat()) * 0.1f,
                            scaleY = 1f + sin(moodGlow * PI.toFloat()) * 0.1f
                        )
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Save Mood Entry",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun RevolutionaryRecentMoods(
    magneticField: Float,
    fluidFlow: Float,
    recentMoods: List<RecentMoodEntry>
) {
    if (recentMoods.isEmpty()) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(18.dp, RoundedCornerShape(28.dp))
            .graphicsLayer(
                scaleX = 1f + sin(magneticField * PI.toFloat()) * 0.005f,
                scaleY = 1f + cos(magneticField * PI.toFloat()) * 0.005f
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(28.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF2A2A3E).copy(alpha = 0.98f),
                            Color(0xFF3A3A4E).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(32.dp)
        ) {
            Column {
                Text(
                    text = "Recent Moods",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    recentMoods.forEach { entry ->
                        RevolutionaryMoodEntry(entry = entry, fluidFlow = fluidFlow)
                    }
                }
            }
        }
    }
}


@Composable
private fun RevolutionaryMoodEntry(
    entry: RecentMoodEntry,
    fluidFlow: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.1f),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .graphicsLayer(
                alpha = 0.8f + fluidFlow * 0.2f
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = entry.emoji,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = entry.mood,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = entry.time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.7f)
            )
        }

        Text(
            text = "${entry.intensity}/10",
            style = MaterialTheme.typography.titleMedium,
            color = entry.color,
            fontWeight = FontWeight.Bold
        )
    }
}





// Data Models
data class Particle(
    val id: Int,
    val initialX: Float,
    val initialY: Float,
    val speed: Float,
    val size: androidx.compose.ui.unit.Dp,
    val color: Color
)

data class RecentMoodEntry(
    val emoji: String,
    val mood: String,
    val time: String,
    val intensity: Int,
    val color: Color
)

// Sample Data
val moods = listOf(
    Triple("😊", "Happy", Color(0xFF4ECDC4)),
    Triple("😔", "Sad", Color(0xFF8B5CF6)),
    Triple("😡", "Angry", Color(0xFFFF6B9D)),
    Triple("😰", "Anxious", Color(0xFFFFD93D)),
    Triple("😴", "Tired", Color(0xFF96CEB4)),
    Triple("🤔", "Confused", Color(0xFFF97316)),
    Triple("😌", "Calm", Color(0xFF10B981)),
    Triple("😤", "Frustrated", Color(0xFFEF4444)),
    Triple("🥰", "Loved", Color(0xFFEC4899))
)

val recentMoodEntries = listOf(
    RecentMoodEntry("😊", "Happy", "2 hours ago", 8, Color(0xFF4ECDC4)),
    RecentMoodEntry("😌", "Calm", "Yesterday", 7, Color(0xFF10B981)),
    RecentMoodEntry("😔", "Sad", "2 days ago", 4, Color(0xFF8B5CF6)),
    RecentMoodEntry("😴", "Tired", "3 days ago", 6, Color(0xFF96CEB4))
)
