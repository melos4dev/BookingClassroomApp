package com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.ProfileEditViewModel

@Composable
fun SaveImage (viewModel: ProfileEditViewModel = hiltViewModel()){

    when (val saveImageResponse = viewModel.saveImageResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            viewModel.onUpdate(saveImageResponse.data)
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                saveImageResponse.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()

        }

        else -> {}
    }

}