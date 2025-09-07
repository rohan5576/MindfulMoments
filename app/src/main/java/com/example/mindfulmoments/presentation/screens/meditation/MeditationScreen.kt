package com.example.mindfulmoments.presentation.screens.meditation

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
import kotlin.math.*
import com.example.mindfulmoments.data.repository.AppDataManager
import com.example.mindfulmoments.data.repository.MeditationSessionData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeditationScreen(
    onNavigateBack: () -> Unit
) {
    var selectedMeditation by remember { mutableStateOf<MeditationSession?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentTime by remember { mutableStateOf(0) }
    var totalTime by remember { mutableStateOf(0) }
    var showSessionDialog by remember { mutableStateOf(false) }
    
    val context = androidx.compose.ui.platform.LocalContext.current
    val appDataManager = remember { AppDataManager(context) }
    
    // Real meditation data from AppDataManager
    val meditationData by appDataManager.meditationData.collectAsState()
    
    // Load real meditation data
    LaunchedEffect(Unit) {
        try {
            appDataManager.refreshAllData()
        } catch (e: Exception) {
            Log.e("MeditationScreen", "Error refreshing data", e)
        }
    }
    
    // Advanced Physics-Based Animations
    val infiniteTransition = rememberInfiniteTransition(label = "advanced_meditation")
    
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
        List(18) { index ->
            Particle(
                id = index,
                initialX = (index * 28f + 45f) % 380f,
                initialY = (index * 22f + 90f) % 520f,
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
        
        // Subtle floating particles
        repeat(5) { index ->
            Box(
                modifier = Modifier
                    .size(3.dp)
                    .offset(
                        x = (particleOffset * 500 + index * 180).dp,
                        y = (particleOffset * 350 + index * 220).dp
                    )
                    .background(
                        Color(0xFF4ECDC4).copy(alpha = 0.4f),
                        CircleShape
                    )
            )
        }
        
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
                PremiumMeditationHeader(onNavigateBack = onNavigateBack)
            }
            
            // Featured Meditation
            item {
                PremiumFeaturedMeditation(
                    meditation = featuredMeditation,
                    onStart = {
                        selectedMeditation = featuredMeditation
                        totalTime = featuredMeditation.durationMinutes * 60
                        showSessionDialog = true
                    }
                )
            }
            
            // Meditation Categories
            item {
                PremiumMeditationCategories(
                    categories = meditationCategories,
                    onCategorySelected = { category ->
                        Log.d("MeditationScreen", "Selected category: $category")
                    }
                )
            }
            
            // Popular Sessions
            item {
                PremiumPopularSessions(
                    sessions = popularSessions,
                    onSessionSelected = { session ->
                        selectedMeditation = session
                        totalTime = session.durationMinutes * 60
                        showSessionDialog = true
                    }
                )
            }
            
            // Daily Challenge
            item {
                PremiumDailyChallenge(
                    challenge = dailyChallenge,
                    onStartChallenge = {
                        selectedMeditation = dailyChallenge
                        totalTime = dailyChallenge.durationMinutes * 60
                        showSessionDialog = true
                    }
                )
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
        
        // Meditation Session Dialog
        if (showSessionDialog && selectedMeditation != null) {
            PremiumMeditationSessionDialog(
                session = selectedMeditation!!,
                onDismiss = { showSessionDialog = false },
                onStart = {
                    showSessionDialog = false
                    isPlaying = true
                    // Start meditation session logic here
                    Log.d("MeditationScreen", "Starting meditation: ${selectedMeditation!!.title}")
                }
            )
        }
    }
}

@Composable
private fun PremiumMeditationHeader(onNavigateBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.3f),
                spotColor = Color(0xFF6EDDD4).copy(alpha = 0.4f)
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
                        text = "Meditation",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Find your inner peace and tranquility",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PremiumFeaturedMeditation(
    meditation: MeditationSession,
    onStart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(
                elevation = 22.dp,
                shape = RoundedCornerShape(26.dp),
                ambientColor = Color(0xFF8B5CF6).copy(alpha = 0.3f)
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
                            Color(0xFF8B5CF6).copy(alpha = 0.9f),
                            Color(0xFFEC4899).copy(alpha = 0.8f),
                            Color(0xFFFF6B9D).copy(alpha = 0.7f)
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🌟",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Column {
                        Text(
                            text = "Featured Session",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = meditation.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Text(
                    text = meditation.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    maxLines = 2
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${meditation.durationMinutes} min",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                    
                    Button(
                        onClick = onStart,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(alpha = 0.2f)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Start",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumMeditationCategories(
    categories: List<MeditationCategory>,
    onCategorySelected: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0xFF2A2A3E).copy(alpha = 0.4f)
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
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                LazyVerticalGrid(
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(160.dp)
                ) {
                    items(categories) { category ->
                        PremiumCategoryCard(
                            category = category,
                            onClick = { onCategorySelected(category.name) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumCategoryCard(
    category: MeditationCategory,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = category.color.copy(alpha = 0.3f)
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
                            category.color.copy(alpha = 0.8f),
                            category.color.copy(alpha = 0.6f)
                        )
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = category.emoji,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun PremiumPopularSessions(
    sessions: List<MeditationSession>,
    onSessionSelected: (MeditationSession) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0xFF2A2A3E).copy(alpha = 0.4f)
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
                    text = "Popular Sessions",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                
                sessions.forEach { session ->
                    PremiumSessionItem(
                        session = session,
                        onClick = { onSessionSelected(session) }
                    )
                    if (session != sessions.last()) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PremiumSessionItem(
    session: MeditationSession,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        session.color.copy(alpha = 0.3f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = session.emoji,
                    fontSize = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = session.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = session.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                    maxLines = 1
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${session.durationMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun PremiumDailyChallenge(
    challenge: MeditationSession,
    onStartChallenge: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0xFFFFD700).copy(alpha = 0.3f)
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
                            Color(0xFFFFD700).copy(alpha = 0.9f),
                            Color(0xFFFFA500).copy(alpha = 0.8f),
                            Color(0xFFFF8C00).copy(alpha = 0.7f)
                        )
                    )
                )
                .padding(28.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🏆",
                    fontSize = 48.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Daily Challenge",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = challenge.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Button(
                    onClick = onStartChallenge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.2f)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Start Challenge",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun PremiumMeditationSessionDialog(
    session: MeditationSession,
    onDismiss: () -> Unit,
    onStart: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF2A2A3E),
        titleContentColor = Color.White,
        textContentColor = Color.White.copy(alpha = 0.9f),
        title = {
            Text(
                text = "Start Meditation Session",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        text = session.emoji,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Column {
                        Text(
                            text = session.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${session.durationMinutes} minutes",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
                Text(
                    text = session.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Find a comfortable position, close your eyes, and let this guided session help you find inner peace.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onStart,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4ECDC4)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Begin Session", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Text("Cancel")
            }
        }
    )
}

// Data Models
data class MeditationSession(
    val id: String,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val emoji: String,
    val color: Color,
    val category: String
)

data class MeditationCategory(
    val name: String,
    val emoji: String,
    val color: Color
)

// Real Meditation Data - Enhanced with authentic content
val featuredMeditation = MeditationSession(
    id = "1",
    title = "Mindful Morning Awakening",
    description = "Begin your day with intention and presence. This guided meditation helps you set a positive tone for the day ahead, cultivating awareness and gratitude for the new opportunities that await.",
    durationMinutes = 10,
    emoji = "🌅",
    color = Color(0xFF4ECDC4),
    category = "Morning"
)

val meditationCategories = listOf(
    MeditationCategory("Morning", "🌅", Color(0xFF4ECDC4)),
    MeditationCategory("Sleep", "🌙", Color(0xFF8B5CF6)),
    MeditationCategory("Stress Relief", "🧘", Color(0xFFFF6B9D)),
    MeditationCategory("Focus & Clarity", "🎯", Color(0xFFFFD700)),
    MeditationCategory("Anxiety & Calm", "😌", Color(0xFF87CEEB)),
    MeditationCategory("Gratitude", "🙏", Color(0xFF96CEB4)),
    MeditationCategory("Mindfulness", "🌿", Color(0xFF10B981)),
    MeditationCategory("Compassion", "💝", Color(0xFFEC4899))
)

val popularSessions = listOf(
    MeditationSession(
        id = "2",
        title = "4-7-8 Breathing Technique",
        description = "Master the 4-7-8 breathing pattern for instant stress relief and better sleep. This ancient technique helps regulate your nervous system and brings immediate calm.",
        durationMinutes = 5,
        emoji = "💨",
        color = Color(0xFF4ECDC4),
        category = "Breathing"
    ),
    MeditationSession(
        id = "3",
        title = "Progressive Body Scan",
        description = "Journey through your body with mindful awareness, releasing tension from head to toe. Perfect for deep relaxation and body-mind connection.",
        durationMinutes = 15,
        emoji = "👤",
        color = Color(0xFF8B5CF6),
        category = "Relaxation"
    ),
    MeditationSession(
        id = "4",
        title = "Metta (Loving-Kindness)",
        description = "Cultivate unconditional love and compassion for yourself and others. This traditional Buddhist practice opens your heart and builds emotional resilience.",
        durationMinutes = 12,
        emoji = "💝",
        color = Color(0xFFFF6B9D),
        category = "Compassion"
    ),
    MeditationSession(
        id = "5",
        title = "Mindful Walking Meditation",
        description = "Transform ordinary walking into a profound meditation practice. Learn to walk with awareness, finding peace in movement and connection with the earth.",
        durationMinutes = 20,
        emoji = "🚶",
        color = Color(0xFF96CEB4),
        category = "Movement"
    ),
    MeditationSession(
        id = "6",
        title = "Mindfulness of Breath",
        description = "Anchor your attention to the natural rhythm of your breath. This foundational practice builds concentration and present-moment awareness.",
        durationMinutes = 8,
        emoji = "🫁",
        color = Color(0xFF10B981),
        category = "Mindfulness"
    ),
    MeditationSession(
        id = "7",
        title = "Gratitude Practice",
        description = "Cultivate appreciation for life's blessings through mindful reflection. This practice shifts your perspective and increases happiness and contentment.",
        durationMinutes = 10,
        emoji = "🙏",
        color = Color(0xFF96CEB4),
        category = "Gratitude"
    )
)

val dailyChallenge = MeditationSession(
    id = "8",
    title = "7-Day Mindfulness Challenge",
    description = "Embark on a transformative journey of daily mindfulness practice. Each day builds upon the previous, creating lasting habits that enhance your well-being and mental clarity.",
    durationMinutes = 7,
    emoji = "🏆",
    color = Color(0xFFFFD700),
    category = "Challenge"
)

// Comprehensive meditation library by category
val morningMeditations = listOf(
    MeditationSession(
        id = "9",
        title = "Sunrise Intention Setting",
        description = "Greet the new day with purpose and clarity. Set meaningful intentions that align with your values and aspirations.",
        durationMinutes = 12,
        emoji = "🌅",
        color = Color(0xFF4ECDC4),
        category = "Morning"
    ),
    MeditationSession(
        id = "10",
        title = "Gentle Wake-Up Flow",
        description = "Ease into your day with gentle movements and mindful awareness. Perfect for those who prefer a gradual transition from sleep to activity.",
        durationMinutes = 8,
        emoji = "🌅",
        color = Color(0xFF4ECDC4),
        category = "Morning"
    )
)

val sleepMeditations = listOf(
    MeditationSession(
        id = "11",
        title = "Deep Sleep Induction",
        description = "Release the day's tensions and prepare your mind and body for restorative sleep. This guided practice helps you drift into peaceful slumber.",
        durationMinutes = 15,
        emoji = "🌙",
        color = Color(0xFF8B5CF6),
        category = "Sleep"
    ),
    MeditationSession(
        id = "12",
        title = "Insomnia Relief",
        description = "For those struggling with sleep, this practice helps quiet racing thoughts and creates the mental space needed for rest.",
        durationMinutes = 20,
        emoji = "🌙",
        color = Color(0xFF8B5CF6),
        category = "Sleep"
    )
)

val stressReliefMeditations = listOf(
    MeditationSession(
        id = "13",
        title = "Quick Stress Reset",
        description = "A powerful 5-minute practice to instantly reduce stress and anxiety. Perfect for busy moments when you need immediate relief.",
        durationMinutes = 5,
        emoji = "🧘",
        color = Color(0xFFFF6B9D),
        category = "Stress Relief"
    ),
    MeditationSession(
        id = "14",
        title = "Progressive Muscle Relaxation",
        description = "Systematically release tension from every muscle group. This practice is especially effective for physical stress and anxiety.",
        durationMinutes = 18,
        emoji = "🧘",
        color = Color(0xFFFF6B9D),
        category = "Stress Relief"
    )
)

val focusMeditations = listOf(
    MeditationSession(
        id = "15",
        title = "Concentration Builder",
        description = "Strengthen your ability to focus and maintain attention. This practice is ideal for students, professionals, and anyone seeking mental clarity.",
        durationMinutes = 10,
        emoji = "🎯",
        color = Color(0xFFFFD700),
        category = "Focus & Clarity"
    ),
    MeditationSession(
        id = "16",
        title = "Mindful Work Break",
        description = "Take a refreshing mental break that actually improves your productivity. Reset your mind and return to work with renewed focus.",
        durationMinutes = 6,
        emoji = "🎯",
        color = Color(0xFFFFD700),
        category = "Focus & Clarity"
    )
)

val anxietyMeditations = listOf(
    MeditationSession(
        id = "17",
        title = "Anxiety Relief Protocol",
        description = "A comprehensive approach to managing anxiety through mindfulness. Learn to observe anxious thoughts without being overwhelmed by them.",
        durationMinutes = 15,
        emoji = "😌",
        color = Color(0xFF87CEEB),
        category = "Anxiety & Calm"
    ),
    MeditationSession(
        id = "18",
        title = "Calm the Nervous System",
        description = "Activate your body's natural relaxation response. This practice helps regulate your nervous system and brings deep calm.",
        durationMinutes = 12,
        emoji = "😌",
        color = Color(0xFF87CEEB),
        category = "Anxiety & Calm"
    )
)

val gratitudeMeditations = listOf(
    MeditationSession(
        id = "19",
        title = "Daily Gratitude Practice",
        description = "Cultivate a grateful heart through mindful reflection. This practice increases happiness and helps you appreciate life's simple pleasures.",
        durationMinutes = 8,
        emoji = "🙏",
        color = Color(0xFF96CEB4),
        category = "Gratitude"
    ),
    MeditationSession(
        id = "20",
        title = "Gratitude for Challenges",
        description = "Learn to find gratitude even in difficult times. This practice helps you grow through adversity and build resilience.",
        durationMinutes = 10,
        emoji = "🙏",
        color = Color(0xFF96CEB4),
        category = "Gratitude"
    )
)

val mindfulnessMeditations = listOf(
    MeditationSession(
        id = "21",
        title = "Present Moment Awareness",
        description = "Learn to live fully in the present moment. This foundational practice helps you break free from dwelling on the past or worrying about the future.",
        durationMinutes = 12,
        emoji = "🌿",
        color = Color(0xFF10B981),
        category = "Mindfulness"
    ),
    MeditationSession(
        id = "22",
        title = "Mindful Eating",
        description = "Transform your relationship with food through mindful awareness. Learn to eat with presence and appreciation for nourishment.",
        durationMinutes = 8,
        emoji = "🌿",
        color = Color(0xFF10B981),
        category = "Mindfulness"
    )
)

val compassionMeditations = listOf(
    MeditationSession(
        id = "23",
        title = "Self-Compassion Break",
        description = "Learn to treat yourself with the same kindness you would offer a dear friend. This practice builds emotional resilience and self-acceptance.",
        durationMinutes = 10,
        emoji = "💝",
        color = Color(0xFFEC4899),
        category = "Compassion"
    ),
    MeditationSession(
        id = "24",
        title = "Compassion for Others",
        description = "Extend your compassion beyond yourself to include all beings. This practice opens your heart and connects you with the world around you.",
        durationMinutes = 15,
        emoji = "💝",
        color = Color(0xFFEC4899),
        category = "Compassion"
    )
)

// All meditation sessions combined
val allMeditationSessions = listOf(
    featuredMeditation,
    *popularSessions.toTypedArray(),
    dailyChallenge,
    *morningMeditations.toTypedArray(),
    *sleepMeditations.toTypedArray(),
    *stressReliefMeditations.toTypedArray(),
    *focusMeditations.toTypedArray(),
    *anxietyMeditations.toTypedArray(),
    *gratitudeMeditations.toTypedArray(),
    *mindfulnessMeditations.toTypedArray(),
    *compassionMeditations.toTypedArray()
)

data class Particle(
    val id: Int,
    val initialX: Float,
    val initialY: Float,
    val speed: Float,
    val size: androidx.compose.ui.unit.Dp,
    val color: Color
)
