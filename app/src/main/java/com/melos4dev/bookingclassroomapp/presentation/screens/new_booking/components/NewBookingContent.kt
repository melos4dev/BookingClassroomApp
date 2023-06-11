package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.RoomPreferences
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultAlertDialog
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultButton
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultDatePickerDialog
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultDropDownMenu
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTextField
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components.AddBookingDialog
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor


@Composable
fun NewBookingContent(
    navController: NavHostController,
    viewModel: NewBookingViewModel = hiltViewModel()
) {

    val state = viewModel.state

    var openDialogDate by remember { mutableStateOf(true) }
    var openDialogAddBooking by remember { mutableStateOf(false) }
    var openAlertDialog by remember { mutableStateOf(false) }



    DefaultAlertDialog(
        show = openAlertDialog,
        title = "USUARIO BLOQUEADO",
        message = "En estos momentos no puedes reservar aulas, tu usuario tiene restringidas las reservas, ponte en contacto con jefatura",
        onConfirm = {
            openAlertDialog = false
            navController?.popBackStack()
        })

    DefaultDatePickerDialog(
        openDialog = openDialogDate,
        onDismiss = { openDialogDate = false },
        onConfirmOrCancel = { state.date = it }
    )

    AddBookingDialog(
        show = openDialogAddBooking,
        onDismiss = {
            openDialogAddBooking = false
            viewModel.listOfAvailableClassrooms = mutableListOf()
        },
        navController = navController,
        viewModel = viewModel
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Icon(
                imageVector = Icons.Default.EventAvailable,
                contentDescription = "Add Booking",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 32.dp),
                tint = DarkPrimaryColor
            )

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
                        text = "RESERVAR AULA",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Por favor rellena todos los campos",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //FECHA

                    DefaultTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { openDialogDate = true },
                        value = state.date,
                        onValueChange = { state.date = it },
                        label = "Fecha de la Reserva",
                        icon = Icons.Default.CalendarMonth,
                        enabled = false,
                        readOnly = true

                    )
                    //MENU TIPO DE AULA

                    DefaultDropDownMenu(
                        onValueChange = { state.classroomType = it },
                        listItems = viewModel.listClassroomType,
                        label = "Tipo de Aula",
                        icon = Icons.Default.RoomPreferences
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //MENU HORAS

                    DefaultDropDownMenu(
                        onValueChange = { state.hour = it },
                        listItems = viewModel.listHour,
                        label = "Hora de la reserva",
                        icon = Icons.Default.Watch
                    )

                    Spacer(modifier = Modifier.height(16.dp))

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

                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp),
                        text = "BUSCAR AULA DISPONIBLE",
                        onClick = {
                            if (viewModel.user.isLocked) {
                                openAlertDialog = true

                            } else {
                                viewModel.getAvailableClassrooms()
                                openDialogAddBooking = true
                            }
                        },
                        enabled = viewModel.validateInputs()
                    )

                }
            }
        }
    }

}



