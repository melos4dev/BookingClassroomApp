package com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.melos4dev.bookingclassroomapp.domain.model.Incidence
import com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences.MyIncidencesViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DividerColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MyGreenColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MyRedColor
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.convertDateToString

@Composable
fun IncidenceCard(incidence: Incidence, viewModel: MyIncidencesViewModel = hiltViewModel()) {

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
                if (incidence.isFixed) {
                    Box(
                        modifier = Modifier
                            .background(color = MyGreenColor, shape = CircleShape)
                            .size(40.dp)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            Icons.Filled.Verified,
                            "Incidence solved Icon",
                            tint = Color.White
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .background(color = MyRedColor, shape = CircleShape)
                            .size(40.dp)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            Icons.Filled.Warning,
                            "Incidence Icon",
                            tint = Color.White
                        )
                    }
                }


                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(6f)
                ) {
                    // Asunto
                    Text(
                        text = incidence.classroomName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    // Fecha
                    Text(
                        text = convertDateToString(incidence.date!!),
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

            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 8.dp)) {

                    Text(
                        text = "Estado de la incidencia -> ",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = if (incidence.isFixed) "resuelta" else "pendiente",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                // Imagen
                if (incidence.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        model = incidence.image,
                        contentDescription = "Selected Image",
                        contentScale = ContentScale.Crop
                    )
                }



                    Column(Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 24.dp)
                    ) {
                        Text(
                            text = "Descripción de la incidencia",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = incidence.description,
                            style = MaterialTheme.typography.bodyMedium,
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(
                            Modifier
                                .background(DividerColor)
                                .height(1.dp)

                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Que permita borrar si la incidencia no esta solucionda

                        if (!incidence.isFixed) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.End)
                            ) {

                                IconButton(
                                    onClick = {
                                        viewModel.delete(incidence.id)
                                        expandedState = false
                                    },
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete Icon",
                                        tint = Color.Red,
                                        modifier = Modifier.size(64.dp)
                                    )

                                }

                            }

                        } else {
                        Text(
                            text = "Solución",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = incidence.response,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))


    }

}





