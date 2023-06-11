package com.melos4dev.bookingclassroomapp.presentation.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.screens.notifications.NotificationsViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.notifications.components.GetNotifications


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun NotificationsScreen(navController: NavHostController, viewModel: NotificationsViewModel = hiltViewModel()) {

    Scaffold(
        topBar = { },
        bottomBar = {
        }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            GetNotifications()
        }
    }


}