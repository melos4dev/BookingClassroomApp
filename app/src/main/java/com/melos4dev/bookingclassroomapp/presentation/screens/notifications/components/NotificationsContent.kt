package com.melos4dev.bookingclassroomapp.presentation.screens.notifications.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melos4dev.bookingclassroomapp.domain.model.Notification

@Composable
fun NotificationsContent(notifications: List<Notification>) {

    LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
        items(items = notifications.sortedByDescending { it.date }) { notification ->
            PostCard(notification)
        }

    }
}