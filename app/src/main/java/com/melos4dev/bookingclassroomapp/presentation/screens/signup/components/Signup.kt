package com.melos4dev.bookingclassroomapp.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultProgressBar
import com.melos4dev.bookingclassroomapp.presentation.navigation.Graph
import com.melos4dev.bookingclassroomapp.presentation.screens.signup.SignupViewModel

@Composable
fun Signup(navController: NavHostController, viewModel: SignupViewModel = hiltViewModel()) {

    when (val signupResponse = viewModel.signupResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController.popBackStack(Graph.AUTHENTICATION, true)
                navController.navigate(route = Graph.HOME)
            }

        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                signupResponse.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}