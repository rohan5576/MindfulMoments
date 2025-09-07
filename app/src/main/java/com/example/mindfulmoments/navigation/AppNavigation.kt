package com.example.mindfulmoments.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mindfulmoments.presentation.screens.onboarding.OnboardingScreen
import com.example.mindfulmoments.presentation.screens.profile.UserProfileScreen
import com.example.mindfulmoments.presentation.screens.main.MainDashboardScreen
import com.example.mindfulmoments.presentation.screens.mood.MoodTrackingScreen
import com.example.mindfulmoments.presentation.screens.meditation.MeditationScreen
import com.example.mindfulmoments.presentation.screens.breathing.BreathingScreen
import com.example.mindfulmoments.presentation.screens.insights.InsightsScreen
import com.example.mindfulmoments.presentation.screens.dashboard.DashboardScreen
import android.util.Log

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object UserProfile : Screen("user_profile")
    object MainDashboard : Screen("main_dashboard")
    object Dashboard : Screen("dashboard")
    object MoodTracking : Screen("mood_tracking")
    object Meditation : Screen("meditation")
    object Breathing : Screen("breathing")
    object Insights : Screen("insights")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            Log.d("AppNavigation", "Showing OnboardingScreen")
            OnboardingScreen(
                onOnboardingComplete = {
                    Log.d("AppNavigation", "Onboarding complete callback triggered")
                    navController.navigate(Screen.UserProfile.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                    Log.d("AppNavigation", "Navigation to UserProfile completed")
                }
            )
        }
        
        composable(Screen.UserProfile.route) {
            Log.d("AppNavigation", "Showing UserProfileScreen")
            UserProfileScreen(
                onProfileComplete = {
                    Log.d("AppNavigation", "User profile complete callback triggered")
                    navController.navigate(Screen.MainDashboard.route) {
                        popUpTo(Screen.UserProfile.route) { inclusive = true }
                    }
                    Log.d("AppNavigation", "Navigation to MainDashboard completed")
                }
            )
        }
        
        composable(Screen.MainDashboard.route) {
            Log.d("AppNavigation", "Showing MainDashboardScreen")
            MainDashboardScreen(
                onNavigateToMoodTracking = {
                    navController.navigate(Screen.MoodTracking.route)
                },
                onNavigateToMeditation = {
                    navController.navigate(Screen.Meditation.route)
                },
                onNavigateToBreathing = {
                    navController.navigate(Screen.Breathing.route)
                },
                onNavigateToInsights = {
                    navController.navigate(Screen.Insights.route)
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToMoodTracking = {
                    navController.navigate(Screen.MoodTracking.route)
                },
                onNavigateToMeditation = {
                    navController.navigate(Screen.Meditation.route)
                },
                onNavigateToBreathing = {
                    navController.navigate(Screen.Breathing.route)
                }
            )
        }
        
        composable(Screen.MoodTracking.route) {
            MoodTrackingScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Meditation.route) {
            MeditationScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Breathing.route) {
            BreathingScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Insights.route) {
            InsightsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
