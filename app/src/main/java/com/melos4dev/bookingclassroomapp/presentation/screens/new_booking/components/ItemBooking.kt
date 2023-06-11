package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.domain.model.Classroom
import com.melos4dev.bookingclassroomapp.presentation.screens.new_booking.NewBookingViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor

@Composable
fun ItemBooking(
    classroom: Classroom,
    viewModel: NewBookingViewModel,
    navController: NavHostController,
    onDismiss: () -> Unit,
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBFE))
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "Aula: " + classroom.classroomName,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = classroom.floor,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 16.dp),
                )
                Text(
                    text = classroom.zone, modifier = Modifier
                        .padding(top = 4.dp, start = 16.dp)
                )
            }
            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    viewModel.onNewBooking(classroom)
                    navController?.popBackStack()
                    onDismiss()
                }) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Reservar",
                    fontWeight = FontWeight.SemiBold,
                    color = DarkPrimaryColor
                )
            }
        }

    }
}




