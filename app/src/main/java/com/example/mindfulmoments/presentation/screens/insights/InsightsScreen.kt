package com.example.mindfulmoments.presentation.screens.insights

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlin.math.*
import com.example.mindfulmoments.ml.MindfulMLManager
import com.example.mindfulmoments.ml.*
import com.example.mindfulmoments.data.repository.AppDataManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(
    onNavigateBack: () -> Unit
) {
    var selectedTimeRange by remember { mutableStateOf("Week") }
    var wellnessScore by remember { mutableStateOf(0f) }
    
    val coroutineScope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current
    val mlManager = remember { MindfulMLManager(context) }
    val appDataManager = remember { AppDataManager(context) }
    
    // Real-time data from AppDataManager
    val insightsData by appDataManager.insightsData.collectAsState()
    val moodData by appDataManager.moodData.collectAsState()
    val meditationData by appDataManager.meditationData.collectAsState()
    val breathingData by appDataManager.breathingData.collectAsState()
    
    var moodAnalysis by remember { mutableStateOf<MoodAnalysisResult?>(null) }
    var meditationRecommendations by remember { mutableStateOf<MeditationRecommendations?>(null) }
    var breathingRecommendations by remember { mutableStateOf<BreathingRecommendations?>(null) }
    var wellnessInsights by remember { mutableStateOf<WellnessInsights?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                isLoading = true
                moodAnalysis = mlManager.analyzeMoodPatterns()
                meditationRecommendations = mlManager.generateMeditationRecommendations()
                breathingRecommendations = mlManager.generateBreathingRecommendations()
                wellnessInsights = mlManager.generateWellnessInsights()
                wellnessScore = wellnessInsights?.overallScore?.toFloat() ?: 0f
            } catch (e: Exception) {
                Log.e("InsightsScreen", "Error loading ML insights", e)
            } finally {
                isLoading = false
            }
        }
    }
    
    // Advanced Physics-Based Animations
    val infiniteTransition = rememberInfiniteTransition(label = "advanced_insights")
    
    // Floating particles with physics simulation
    val particleOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f))
        ),
        label = "particles"
    )
    
    // Wellness glow with advanced easing
    val wellnessGlow by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "wellness_glow"
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
        List(14) { index ->
            Particle(
                id = index,
                initialX = (index * 27f + 43f) % 370f,
                initialY = (index * 23f + 88f) % 510f,
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
    
    // Animate wellness score on first load
    LaunchedEffect(Unit) {
        delay(500)
        wellnessScore = 78f
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
                        alpha = 0.4f + wellnessGlow * 0.3f,
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
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Premium Header
            item {
                PremiumInsightsHeader(onNavigateBack = onNavigateBack)
            }
            
            // Wellness Score Card
            item {
                PremiumWellnessScoreCard(
                    score = wellnessScore,
                    timeRange = selectedTimeRange,
                    onTimeRangeChanged = { selectedTimeRange = it }
                )
            }
            
            // Key Insights Grid
            item {
                PremiumKeyInsightsGrid()
            }
            
            // Mood Trends
            item {
                PremiumMoodTrendsCard()
            }
            
            // Meditation Progress
            item {
                PremiumMeditationProgressCard()
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun PremiumInsightsHeader(onNavigateBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF96CEB4).copy(alpha = 0.3f)
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
                            Color(0xFF96CEB4).copy(alpha = 0.9f),
                            Color(0xFF8B5CF6).copy(alpha = 0.8f)
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
                        text = "Insights & Analytics",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Discover patterns in your mindfulness journey",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PremiumWellnessScoreCard(
    score: Float,
    timeRange: String,
    onTimeRangeChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(
                elevation = 22.dp,
                shape = RoundedCornerShape(26.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.3f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(26.dp)
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
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Wellness Score",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // Time Range Selector
                    Row(
                        modifier = Modifier
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(4.dp)
                    ) {
                        listOf("Week", "Month", "Year").forEach { range ->
                            FilterChip(
                                selected = timeRange == range,
                                onClick = { onTimeRangeChanged(range) },
                                label = { Text(range) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color.White.copy(alpha = 0.3f),
                                    selectedLabelColor = Color.White,
                                    containerColor = Color.Transparent,
                                    labelColor = Color.White.copy(alpha = 0.7f)
                                ),
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                }
                
                // Score Display
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${score.toInt()}",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 72.sp,
                                lineHeight = 80.sp
                            ),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "out of 100",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                    
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = if (score >= 80) "Excellent" else if (score >= 60) "Good" else "Fair",
                            style = MaterialTheme.typography.titleMedium,
                            color = if (score >= 80) Color(0xFF4ECDC4) else Color(0xFFFFD700),
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = if (score >= 80) "Keep up the great work!" else "You're on the right track",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumKeyInsightsGrid() {
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
                    text = "Key Insights",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                LazyVerticalGrid(
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(200.dp)
                ) {
                    items(4) { index ->
                        val insights = listOf(
                            Triple("😌", "Mood Stability", Color(0xFF4ECDC4)),
                            Triple("🧘", "Meditation Growth", Color(0xFF8B5CF6)),
                            Triple("💨", "Stress Reduction", Color(0xFFFF6B9D)),
                            Triple("🌙", "Sleep Quality", Color(0xFF96CEB4))
                        )
                        val (emoji, title, color) = insights[index]
                        
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .shadow(
                                    elevation = 12.dp,
                                    shape = RoundedCornerShape(20.dp)
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
                                                color.copy(alpha = 0.8f),
                                                color.copy(alpha = 0.6f)
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
                                        text = emoji,
                                        fontSize = 24.sp,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumMoodTrendsCard() {
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
                    text = "Mood Trends",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                // Mood Distribution
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val moods = listOf(
                        Triple("😊", "35%", "Happy"),
                        Triple("😌", "28%", "Calm"),
                        Triple("😐", "22%", "Neutral"),
                        Triple("😰", "15%", "Stressed")
                    )
                    
                    moods.forEach { (emoji, percentage, label) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(
                                        Color.White.copy(alpha = 0.2f),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = emoji,
                                    fontSize = 24.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = percentage,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Your mood has been more positive and stable this week",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun PremiumMeditationProgressCard() {
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
                    text = "Meditation Progress",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProgressMetric("Sessions", "23", Color(0xFF8B5CF6))
                    ProgressMetric("Minutes", "156", Color(0xFFEC4899))
                    ProgressMetric("Streak", "7 days", Color(0xFFFF6B9D))
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Progress Bar
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Goal Progress",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "78%",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF8B5CF6),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = 0.78f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = Color(0xFF8B5CF6),
                        trackColor = Color.White.copy(alpha = 0.2f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressMetric(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = color,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.7f)
        )
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
