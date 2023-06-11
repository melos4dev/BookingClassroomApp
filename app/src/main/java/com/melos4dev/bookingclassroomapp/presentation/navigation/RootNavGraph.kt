package com.melos4dev.bookingclassroomapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melos4dev.bookingclassroomapp.presentation.screens.home.HomeScreen
import com.melos4dev.bookingclassroomapp.presentation.navigation.Graph
import com.melos4dev.bookingclassroomapp.presentation.navigation.authNavGraph

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        route = Graph.ROOT
    ) {

        authNavGraph(navController = navController)

        composable(route = Graph.HOME) {
            HomeScreen()
        }


    }

}
