package com.melos4dev.bookingclassroomapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.MyBookingsScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.MyIncidencesScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.notifications.NotificationsScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.profile.ProfileScreen

@Composable

fun HomeBottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.MyBookings.route
    ) {

        composable(route = HomeBottomBarScreen.MyBookings.route) {
            MyBookingsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.MyIncidences.route) {
           MyIncidencesScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Notifications.route) {
            NotificationsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }

        detailsNavGraph(navController)

        authNavGraph(navController)
    }

}

sealed class HomeBottomBarScreen(
    val route:String,
    val title:String,
    val icon : ImageVector
){
    object Notifications: HomeBottomBarScreen(
        route= "notifications",
        title = "Avisos",
        icon = Icons.Default.Notifications
    )

    object MyBookings: HomeBottomBarScreen(
        route= "my_bookings",
        title = "Reservas",
        icon = Icons.Default.School
    )

    object MyIncidences: HomeBottomBarScreen(
        route= "my_incidences",
        title = "Incidencias",
        icon = Icons.Default.ReportProblem
    )

    object Profile: HomeBottomBarScreen(
        route= "profile",
        title = "Perfil",
        icon = Icons.Default.AccountCircle
    )

}
