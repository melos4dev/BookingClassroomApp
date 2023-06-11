package com.melos4dev.bookingclassroomapp.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.melos4dev.bookingclassroomapp.presentation.navigation.Graph
import com.melos4dev.bookingclassroomapp.presentation.screens.login.LoginScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.signup.SignupScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.splash.SplashScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Splash.route

    ) {
        composable(route = AuthScreen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = AuthScreen.Signup.route) {
            SignupScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Splash : AuthScreen("splash")
    object Login : AuthScreen("login")
    object Signup : AuthScreen("signup")
}
