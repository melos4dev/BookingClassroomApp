package com.melos4dev.bookingclassroomapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MediumPrimaryColor

@Composable
fun DefaultProgressBar(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(color = MediumPrimaryColor)
    }
}