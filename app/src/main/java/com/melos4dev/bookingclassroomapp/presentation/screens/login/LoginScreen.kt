package com.melos4dev.bookingclassroomapp.presentation.screens.login


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.screens.login.components.Login
import com.melos4dev.bookingclassroomapp.presentation.screens.login.components.LoginBottomBar
import com.melos4dev.bookingclassroomapp.presentation.screens.login.components.LoginContent
import com.melos4dev.bookingclassroomapp.presentation.screens.login.components.LoginTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    Scaffold(
        topBar = { LoginTopBar() },
        bottomBar = {
            LoginBottomBar(navController)
        }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { LoginContent() }
    }

    // MANEJAR EL ESTADO DE LA PETICION DE LOGIN
    Login(navController)

}

