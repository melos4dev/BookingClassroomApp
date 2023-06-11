package com.melos4dev.bookingclassroomapp.presentation.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.screens.profile.ProfileViewModel
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTopBar
import com.melos4dev.bookingclassroomapp.presentation.screens.profile.components.ProfileContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "PERFIL DE USUARIO",
                upAvailable = false
            )
        },
        bottomBar = {}
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            ProfileContent(navController = navController)

        }
    }

}