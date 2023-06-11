package com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.MyIncidencesViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.components.MyIncidencesContent

@Composable
fun GetMyIncidences(viewModel: MyIncidencesViewModel = hiltViewModel()) {

    when (val response = viewModel.incidencesResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            MyIncidencesContent(response.data)
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