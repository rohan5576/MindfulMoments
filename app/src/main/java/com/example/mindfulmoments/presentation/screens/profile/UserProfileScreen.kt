package com.example.mindfulmoments.presentation.screens.profile

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

@Composable
fun UserProfileScreen(
    onProfileComplete: () -> Unit
) {
    // State variables
    var userName by remember { mutableStateOf("") }
    var userAge by remember { mutableStateOf("") }
    var selectedGoal by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }
    
    // Update form validation
    LaunchedEffect(userName, selectedGoal) {
        isFormValid = userName.isNotBlank() && selectedGoal.isNotBlank()
    }
    
    // Get context for SharedPreferences
    val context = LocalContext.current
    
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
                            Color(0xFF1A1A3E),
                            Color(0xFF2A2A5E),
                            Color(0xFF3A3A7E),
                            Color(0xFF4A4A9E),
                            Color(0xFF1A1A3E)
                        )
                    )
                )
        )
        
        // Advanced Animated Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4ECDC4).copy(alpha = 0.15f),
                            Color(0xFF8B5CF6).copy(alpha = 0.12f),
                            Color(0xFFFF6B9D).copy(alpha = 0.10f),
                            Color(0xFF96CEB4).copy(alpha = 0.08f),
                            Color.Transparent
                        )
                    )
                )
        )
        
        // Advanced Physics-Based Animations
        val infiniteTransition = rememberInfiniteTransition(label = "profile_animations")
        
        // Form glow with advanced easing
        val formGlow by infiniteTransition.animateFloat(
            initialValue = 0.2f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = androidx.compose.animation.core.CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)),
                repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
            ),
            label = "form_glow"
        )
        
        // Magnetic field effect
        val magneticField by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = androidx.compose.animation.core.CubicBezierEasing(0.68f, -0.55f, 0.265f, 1.55f))
            ),
            label = "magnetic_field"
        )
        
        // Fluid dynamics simulation
        val fluidFlow by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(15000, easing = androidx.compose.animation.core.CubicBezierEasing(0.23f, 1f, 0.32f, 1f))
            ),
            label = "fluid_flow"
        )
        
        // Floating particles with physics simulation
        val particleOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(20000, easing = androidx.compose.animation.core.CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f))
            ),
            label = "particles"
        )
        
        // Particle System
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
                            x = 0.3f + sin(fluidFlow * 3.14159f) * 0.4f,
                            y = 0.7f + cos(fluidFlow * 3.14159f) * 0.3f
                        ),
                        radius = 600f
                    )
                )
                .blur(radius = 1.dp)
        )
        
        // Main Content with Scrollable Layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 80.dp)
                .padding(bottom = 120.dp) // Increased bottom padding for button
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Header Section
            RevolutionaryProfileHeader(
                formGlow = formGlow,
                magneticField = magneticField
            )
            
            // Profile Form
            RevolutionaryProfileForm(
                userName = userName,
                onUserNameChange = { userName = it },
                userAge = userAge,
                onUserAgeChange = { userAge = it },
                selectedGoal = selectedGoal,
                onGoalSelected = { selectedGoal = it },
                formGlow = formGlow,
                fluidFlow = fluidFlow
            )
            
            // Spacer to ensure content doesn't get cut off
            Spacer(modifier = Modifier.height(40.dp))
        }
        
        // Fixed Continue Button at Bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            RevolutionaryContinueButton(
                isEnabled = isFormValid,
                onClick = {
                    // Save user profile to SharedPreferences (will be replaced with database)
                    val prefs = context.getSharedPreferences("progress_prefs", android.content.Context.MODE_PRIVATE)
                    prefs.edit()
                        .putString("user_name", userName)
                        .putString("user_age", userAge)
                        .putString("user_goal", selectedGoal)
                        .putLong("profile_created", System.currentTimeMillis())
                        .apply()
                    
                    android.util.Log.d("UserProfileScreen", "Saved user profile: $userName, $userAge, $selectedGoal")
                    onProfileComplete()
                },
                formGlow = formGlow,
                magneticField = magneticField
            )
        }
    }
}

@Composable
private fun RevolutionaryProfileHeader(
    formGlow: Float,
    magneticField: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp) // Reduced height
                    .shadow(
            elevation = 35.dp,
            shape = RoundedCornerShape(32.dp),
            ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.5f),
            spotColor = Color(0xFF8B5CF6).copy(alpha = 0.7f)
        )
        .graphicsLayer(
            scaleX = 1f + sin(magneticField * 3.14159f) * 0.02f,
            scaleY = 1f + cos(magneticField * 3.14159f) * 0.02f
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
                .padding(24.dp), // Reduced padding
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Icon
                Box(
                    modifier = Modifier
                        .size(60.dp) // Reduced size
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
                            scaleX = 1f + sin(formGlow * 3.14159f) * 0.1f,
                            scaleY = 1f + cos(formGlow * 3.14159f) * 0.1f,
                            rotationZ = magneticField * 360f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp) // Reduced size
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp)) // Reduced spacing
                
                Text(
                    text = "Tell Us About Yourself",
                    style = MaterialTheme.typography.headlineSmall, // Smaller text
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Let's personalize your mindfulness journey",
                    style = MaterialTheme.typography.bodyMedium, // Smaller text
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun RevolutionaryProfileForm(
    userName: String,
    onUserNameChange: (String) -> Unit,
    userAge: String,
    onUserAgeChange: (String) -> Unit,
    selectedGoal: String,
    onGoalSelected: (String) -> Unit,
    formGlow: Float,
    fluidFlow: Float
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
                scaleX = 1f + sin(fluidFlow * 3.14159f) * 0.01f,
                scaleY = 1f + cos(fluidFlow * 3.14159f) * 0.01f
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
                            Color(0xFF2A2A3E).copy(alpha = 0.99f),
                            Color(0xFF3A3A4E).copy(alpha = 0.96f)
                        )
                    )
                )
                .padding(24.dp) // Reduced padding
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp) // Reduced spacing
            ) {
                // Name Input
                Column {
                    Text(
                        text = "What's your name?",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp)) // Reduced spacing
                    OutlinedTextField(
                        value = userName,
                        onValueChange = onUserNameChange,
                        placeholder = {
                            Text(
                                text = "Enter your name",
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer(
                                alpha = 0.9f + formGlow * 0.1f
                            ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF4ECDC4),
                            unfocusedBorderColor = Color(0xFF4ECDC4).copy(alpha = 0.5f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White.copy(alpha = 0.9f)
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
                
                // Age Input
                Column {
                    Text(
                        text = "How old are you? (Optional)",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp)) // Reduced spacing
                    OutlinedTextField(
                        value = userAge,
                        onValueChange = onUserAgeChange,
                        placeholder = {
                            Text(
                                text = "Enter your age",
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer(
                                alpha = 0.9f + formGlow * 0.1f
                            ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8B5CF6),
                            unfocusedBorderColor = Color(0xFF8B5CF6).copy(alpha = 0.5f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White.copy(alpha = 0.9f)
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }
                
                // Goal Selection
                Column {
                    Text(
                        text = "What's your main goal?",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp)) // Reduced spacing
                    
                    val goals = listOf(
                        "Reduce Stress & Anxiety",
                        "Improve Sleep Quality",
                        "Increase Focus & Concentration",
                        "Emotional Balance",
                        "Better Breathing",
                        "General Wellness"
                    )
                    
                    goals.forEach { goal ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp), // Reduced padding
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedGoal == goal,
                                onClick = { onGoalSelected(goal) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFFF6B9D),
                                    unselectedColor = Color.White.copy(alpha = 0.6f)
                                )
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = goal,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White.copy(alpha = 0.9f),
                                modifier = Modifier.graphicsLayer(
                                    alpha = 0.8f + formGlow * 0.2f
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RevolutionaryContinueButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    formGlow: Float,
    magneticField: Float
) {
    Card(
        onClick = onClick,
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp) // Reduced height
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0xFF4ECDC4).copy(alpha = 0.5f)
            )
            .graphicsLayer(
                scaleX = 1f + sin(magneticField * 3.14159f) * 0.02f,
                scaleY = 1f + cos(magneticField * 3.14159f) * 0.02f
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
                    if (isEnabled) {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF4ECDC4).copy(alpha = 0.9f),
                                Color(0xFF6EDDD4).copy(alpha = 0.8f),
                                Color(0xFF8B5CF6).copy(alpha = 0.7f)
                            )
                        )
                    } else {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF666666).copy(alpha = 0.6f),
                                Color(0xFF888888).copy(alpha = 0.5f)
                            )
                        )
                    }
                )
                .padding(20.dp), // Reduced padding
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp) // Reduced size
                        .graphicsLayer(
                            scaleX = 1f + sin(formGlow * 3.14159f) * 0.1f,
                            scaleY = 1f + sin(formGlow * 3.14159f) * 0.1f
                        )
                )
                
                Spacer(modifier = Modifier.width(12.dp)) // Reduced spacing
                
                Text(
                    text = "Continue to Dashboard",
                    style = MaterialTheme.typography.titleMedium, // Smaller text
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
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


