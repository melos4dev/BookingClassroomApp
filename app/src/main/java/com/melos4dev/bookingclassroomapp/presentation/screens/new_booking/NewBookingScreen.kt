package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingViewModel
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTopBar
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components.GetBookings
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components.GetClassrooms
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components.NewBooking
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components.NewBookingContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewBookingScreen(
    navController: NavHostController,
    viewModel: NewBookingViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nueva Reserva",
                upAvailable = true,
                navController = navController
            )
        },
        bottomBar = {

        },

        ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { NewBookingContent(navController) }
    }

    NewBooking()
    GetBookings()
    GetClassrooms()

}