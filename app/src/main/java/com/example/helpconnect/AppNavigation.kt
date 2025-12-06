package com.example.helpconnect

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helpconnect.ui.screens.auth.LoginScreen
import com.example.helpconnect.ui.screens.auth.SignUpScreen
import com.example.helpconnect.ui.screens.details.DonateScreen
import com.example.helpconnect.ui.screens.details.NgoDetailScreen
import com.example.helpconnect.ui.screens.details.SuccessScreen
import com.example.helpconnect.ui.screens.home.HomeScreen
import com.example.helpconnect.ui.screens.onboarding.OnboardingScreen
import com.example.helpconnect.ui.screens.onboarding.SplashScreen
import com.example.helpconnect.ui.screens.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onNavigateToOnboarding = {
                navController.navigate("onboarding") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("onboarding") {
            OnboardingScreen(onNavigateToLogin = {
                navController.navigate("login") {
                    popUpTo("onboarding") { inclusive = true }
                }
            })
        }
        composable("login") {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate("signup") },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("signup") {
            SignUpScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                onNgoClick = { ngoId ->
                    navController.navigate("details/$ngoId")
                },
                onMenuClick = {
                    navController.navigate("profile")
                }
            )
        }
        composable("details/{ngoId}") { backStackEntry ->
            val ngoId = backStackEntry.arguments?.getString("ngoId") ?: return@composable
            NgoDetailScreen(
                ngoId = ngoId,
                onNavigateUp = { navController.popBackStack() },
                onDonateClick = { navController.navigate("donate/$ngoId") }
            )
        }
        composable("donate/{ngoId}") { backStackEntry ->
            val ngoId = backStackEntry.arguments?.getString("ngoId") ?: return@composable
            DonateScreen(
                ngoId = ngoId,
                onNavigateUp = { navController.popBackStack() },
                onConfirmDonation = { navController.navigate("success") }
            )
        }
        composable("success") {
            SuccessScreen(
                onNavigateHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
        composable("profile") {
            ProfileScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}
