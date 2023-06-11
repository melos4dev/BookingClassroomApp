package com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.MyBookingsViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.convertDateToString

@Composable
fun BookingCard(booking: Booking, viewModel: MyBookingsViewModel = hiltViewModel()) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        // Contenedor
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Miniatura
                Box(
                    modifier = Modifier
                        .background(color = DarkPrimaryColor, shape = CircleShape)
                        .size(40.dp)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.EventAvailable,
                        "Icon",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(6f)
                ) {
                    // Aula
                    Text(
                        text = "Aula: " + booking.classroomName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    // Fecha
                    Text(
                        text = convertDateToString(booking.date!!) + "  -  " + booking.hour + " hora",
                        style = MaterialTheme.typography.titleSmall
                    )

                }

                IconButton(modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .rotate(rotationState),
                    onClick = { expandedState = !expandedState }) {

                    Icon(
                        Icons.Default.ArrowDropDownCircle,
                        "Drop down arrow",
                        tint = DarkPrimaryColor
                    )
                }
            }
        }

        if (expandedState) {
            Column(Modifier.padding(start = 16.dp, end = 24.dp, top = 8.dp)) {

                Text(
                    text = "DATOS DE LA RESERVA",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(Modifier.fillMaxWidth()) {
                    Column() {
                        Row(Modifier.padding(top = 8.dp)) {
                            Text(
                                text = "Planta: ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = booking.floor,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row(Modifier.padding(top = 8.dp)) {
                            Text(
                                text = "Zona: ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = booking.zone,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row(Modifier.padding(top = 8.dp)) {
                            Text(
                                text = "Grupo de alumnos:  ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = booking.studentGroup,
                                style = MaterialTheme.typography.bodyMedium
                            )


                        }
                    }
                    Column(
                        Modifier
                            .fillMaxWidth().padding(top = 16.dp),  horizontalAlignment = Alignment.End) {
                        IconButton(
                            onClick = { viewModel.delete(booking.id) },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.Red,
                                modifier = Modifier.size(64.dp)
                            )

                        }
                    }

                }


            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

}





