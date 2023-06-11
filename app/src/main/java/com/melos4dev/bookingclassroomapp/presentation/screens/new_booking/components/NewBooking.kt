package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingViewModel

@Composable
fun NewBooking(viewModel: NewBookingViewModel = hiltViewModel()) {


    when (val response = viewModel.createBookingResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            Toast.makeText(
                LocalContext.current, "La reserva se ha creado correctamente",
                Toast.LENGTH_LONG
            ).show()

            viewModel.clearForm()

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