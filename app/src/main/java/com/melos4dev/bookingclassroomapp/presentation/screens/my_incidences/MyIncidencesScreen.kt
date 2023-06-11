package com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.navigation.DetailsScreen
import com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.MyBookingsViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.components.GetMyIncidences
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MediumPrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MyIncidencesScreen(navController: NavHostController, viewModel: MyBookingsViewModel = hiltViewModel()) {

    Scaffold(
        topBar = { },
        bottomBar = {
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(DetailsScreen.NewIncidence.route) },
                containerColor = MediumPrimaryColor
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add incidence")

            }
        }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { GetMyIncidences() }
    }

}


