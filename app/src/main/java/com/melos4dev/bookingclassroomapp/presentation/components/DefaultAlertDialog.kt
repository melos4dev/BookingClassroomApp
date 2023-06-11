package com.melos4dev.bookingclassroomapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor

@Composable
fun DefaultAlertDialog(
    show: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onConfirm() }, // Accion cuando el usuario pulse fuera
            containerColor= Color.White,
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                 modifier = Modifier
                        .size(50.dp),
                    tint = Color.Red
                )
            },
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DarkPrimaryColor
                )
            },
            text = { Text(text = message) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(
                        text = "Aceptar",
                        fontWeight = FontWeight.SemiBold,
                        color = DarkPrimaryColor
                    )
                }
            }
        )
    }
}