package com.melos4dev.bookingclassroomapp.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.melos4dev.bookingclassroomapp.presentation.navigation.Graph
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.NewIncidenceScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ) {
        composable(
            route = DetailsScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let {
                ProfileEditScreen(navController, user = it)
            }
        }

        composable(DetailsScreen.NewIncidence.route) {
            NewIncidenceScreen(navController = navController)
        }

        composable(DetailsScreen.NewBooking.route) {
            NewBookingScreen(navController = navController)
        }
    }

}

sealed class DetailsScreen(val route: String) {

    object NewBooking : DetailsScreen("bookings/new")
    object NewIncidence : DetailsScreen("incidences/new")

    object ProfileEdit : DetailsScreen("profile/update/{user}") {
        fun passUser(user: String) = "profile/update/$user"
    }

}