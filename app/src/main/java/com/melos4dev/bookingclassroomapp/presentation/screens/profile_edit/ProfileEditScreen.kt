package com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTopBar
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.components.ProfileEdit
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.components.SaveImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Modificar Perfil",
                upAvailable = true,
                navController = navController
            )
        },
        bottomBar = {}
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { ProfileEditContent(navController = navController) }

    }
    SaveImage()
    ProfileEdit()

}