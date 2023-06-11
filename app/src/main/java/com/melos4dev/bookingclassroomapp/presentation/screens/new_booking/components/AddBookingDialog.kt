package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingViewModel
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components.ItemBooking
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryTextColor

@Composable
fun AddBookingDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    navController: NavHostController,
    viewModel: NewBookingViewModel
) {

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(4.dp),

                ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "LISTADO DE AULAS",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkPrimaryColor
                )
                if (viewModel.listOfAvailableClassrooms.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = "No existen aulas disponibles con los datos solicitados",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryTextColor,
                        textAlign = TextAlign.Center
                    )

                }

                LazyColumn() {
                    items(
                        items = viewModel.listOfAvailableClassrooms,
                        key = { it.classroomName }
                    )
                    { classroom ->
                        ItemBooking(classroom, viewModel, navController) { onDismiss() }
                    }

                }
                TextButton(
                    onClick = { onDismiss() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = "Volver",
                        fontWeight = FontWeight.SemiBold,
                        color = DarkPrimaryColor
                    )
                }
            }
        }
    }
}