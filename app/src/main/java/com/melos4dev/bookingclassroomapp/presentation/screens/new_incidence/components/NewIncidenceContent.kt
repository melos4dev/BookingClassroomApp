package com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultButton
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTextArea
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTextField
import com.melos4dev.bookingclassroomapp.presentation.components.DialogCapturePicture
import com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.NewIncidenceViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MediumPrimaryColor


@Composable
fun NewIncidenceContent(navController: NavHostController, viewModel: NewIncidenceViewModel = hiltViewModel()) {

    val state = viewModel.state

    viewModel.resultingActivityHandler.handle()

    val statusDialog = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = statusDialog,
        takePhoto = { viewModel.takePhoto() }
    ) { viewModel.pickImage() }


    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MediumPrimaryColor)

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //   Spacer(modifier = Modifier.height(8.dp))

                    if (viewModel.state.image != "") {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable { statusDialog.value = true },
                            model = viewModel.state.image,
                            contentDescription = "Selected Image",
                            contentScale = ContentScale.Crop
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Default.AddPhotoAlternate,
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .size(125.dp)
                                .clickable { statusDialog.value = true },
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "AGREGAR IMAGEN DE LA INCIDENCIA",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }

            Card(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                    .border(
                        BorderStroke(2.dp, color = DarkPrimaryColor),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth(),
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
                                top = 24.dp,
                                bottom = 0.dp,
                                start = 0.dp,
                                end = 0.dp
                            ),
                        text = "REPORTAR INCIDENCIA",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Por favor rellena todos los campos",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    DefaultTextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        value = state.classroomName,
                        onValueChange = { viewModel.onClassroomNameInput(it) },
                        label = "Nombre del Aula",
                        icon = Icons.Default.School,
                        errorMsg = viewModel.classroomNameErrMsg,
                        validateField = { viewModel.validateClassroomName() }
                    )
                    DefaultTextField(
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth(),
                        value = state.studentGroup,
                        onValueChange = { viewModel.onStudentGroupInput(it) },
                        label = "Grupo de Alumnos",
                        icon = Icons.Default.Groups,
                        errorMsg = viewModel.studentGroupErrMsg,
                        validateField = { viewModel.validateStudentGroup() }
                    )
                    DefaultTextArea(
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .height(150.dp)
                            .fillMaxWidth(),
                        value = state.description,
                        onValueChange = { viewModel.onDescriptionInput(it) },
                        label = "Descripción de la Incidencia",
                        icon = Icons.Default.Description,
                        errorMsg = viewModel.descriptionErrMsg,
                        validateField = { viewModel.validateDescription() }
                    )

                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        text = "ENVIAR INCIDENCIA",
                        onClick = {viewModel.onNewIncidence()
                            navController?.popBackStack()},
                        enabled = viewModel.isEnabledLoginButton
                    )

                }
            }
        }
    }
}

