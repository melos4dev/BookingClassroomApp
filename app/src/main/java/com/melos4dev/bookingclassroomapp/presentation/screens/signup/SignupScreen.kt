package com.melos4dev.bookingclassroomapp.presentation.screens.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTopBar
import com.melos4dev.bookingclassroomapp.presentation.screens.signup.components.Signup
import com.melos4dev.bookingclassroomapp.presentation.screens.signup.components.SignupContent


@Composable
fun SignupScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Regitrar Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        bottomBar = { }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { SignupContent(navController = navController) }
    }

   Signup(navController = navController)
}

