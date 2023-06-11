package com.melos4dev.bookingclassroomapp.presentation.screens.notifications.components


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
import androidx.compose.material.icons.filled.NotificationImportant
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
import com.melos4dev.bookingclassroomapp.domain.model.Notification
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MediumPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.convertDateToString

@Composable
fun PostCard(notification: Notification) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
          /*  .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )*/,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.large,
        //    border = BorderStroke(1.dp, Color.LightGray)
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
                        .background(color = MediumPrimaryColor, shape = CircleShape)
                        .size(40.dp)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.NotificationImportant,
                        "Important notification icon",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(6f)
                ) {
                    // Asunto
                    Text(
                        text = notification.subject,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    // Fecha
                    Text(
                        text = convertDateToString(notification.date!!),
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
            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 8.dp)) {

                Text(
                    text = notification.message,
                    //       maxLines = 10,
                    //       overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

}





