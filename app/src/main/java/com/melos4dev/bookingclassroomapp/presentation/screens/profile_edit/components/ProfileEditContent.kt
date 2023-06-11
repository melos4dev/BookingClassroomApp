package com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.melos4dev.bookingclassroomapp.R
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultButton
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTextField
import com.melos4dev.bookingclassroomapp.presentation.components.DialogCapturePicture
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MediumPrimaryColor


@Composable
fun ProfileEditContent(
    navController: NavHostController,
    viewModel: ProfileEditViewModel = hiltViewModel()
) {

    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()

    val statusDialog = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = statusDialog,
        takePhoto = { viewModel.takePhoto() }
    ) { viewModel.pickImage() }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                if (viewModel.state.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(CircleShape),
                        model = viewModel.state.image,
                        contentDescription = "Selected Image",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .height(150.dp),
                        painter = painterResource(id = R.drawable.user_avatar),
                        contentDescription = "Imagen de usuario"

                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(
                    onClick = {
                        statusDialog.value = true
                        viewModel.isEnabledButton = true
                    },
                    containerColor = MediumPrimaryColor
                ) {
                    Icon(Icons.Filled.AddAPhoto, "Update Profile Image")
                }


            }
        }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 240.dp)
                .border(
                    BorderStroke(2.dp, color = DarkPrimaryColor),
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = DarkPrimaryColor
            )
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 40.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),
                    text = "EDITAR PERFIL",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 24.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "ACTUALIZAR DATOS",
                    onClick = {
                        viewModel.saveImage()
                    },
                    enabled = viewModel.isEnabledButton
                )
            }
        }
    }
}

