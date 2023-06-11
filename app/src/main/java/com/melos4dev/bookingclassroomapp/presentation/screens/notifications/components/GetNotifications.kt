package com.melos4dev.bookingclassroomapp.presentation.screens.notifications.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.screens.notifications.NotificationsViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.notifications.components.NotificationsContent

@Composable
fun GetNotifications(viewModel: NotificationsViewModel = hiltViewModel()) {

    when (val response = viewModel.notificationsResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            NotificationsContent(notifications = response.data)
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