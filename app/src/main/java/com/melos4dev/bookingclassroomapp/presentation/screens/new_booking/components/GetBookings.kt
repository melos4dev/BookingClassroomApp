package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingViewModel

@Composable
fun GetBookings(viewModel: NewBookingViewModel = hiltViewModel()) {


    when (val response = viewModel.getBookingsResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            viewModel.listOfExistingBookings = response.data
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconido",
                Toast.LENGTH_LONG
            ).show()
        }
        else -> {}
    }
}