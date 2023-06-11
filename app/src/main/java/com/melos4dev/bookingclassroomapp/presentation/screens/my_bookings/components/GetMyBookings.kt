package com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.MyBookingsViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.components.MyBookingsContent

@Composable
fun GetMyBookings(viewModel: MyBookingsViewModel = hiltViewModel()) {

    when (val response = viewModel.bookingsResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            MyBookingsContent(bookings = response.data)
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