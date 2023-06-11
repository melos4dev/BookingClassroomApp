package com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.NewIncidenceViewModel
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTopBar
import com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.components.NewIncidence
import com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.components.NewIncidenceContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewIncidenceScreen(
    navController: NavHostController,
    viewModel: NewIncidenceViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nueva Incidencia",
                upAvailable = true,
                navController = navController
            )
        },
        bottomBar = {

        },

        ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { NewIncidenceContent(navController) }
    }

    NewIncidence()

}