package com.melos4dev.bookingclassroomapp.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.SecondaryTextColor


@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit
) {

    if (status.value) {

        Dialog(onDismissRequest = { status.value = false }) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(24.dp),

            ) {

                Text(
                    text = "IMAGEN DE PERFIL",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = DarkPrimaryColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Por favor elija una opción",
                    fontSize = 12.sp,
                    color = SecondaryTextColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.PhotoCamera, contentDescription = "Camera Icon")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Tomar imagen de la cámara", Modifier.clickable {
                        status.value = false
                        takePhoto()
                    })
                }

                Spacer(modifier = Modifier.size(24.dp))

                Row(Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.Image, contentDescription = "Camera Icon")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Elegir imagen de la galería", Modifier.clickable {
                        status.value = false
                        pickImage()
                    })
                }

            }

        }

    }

}