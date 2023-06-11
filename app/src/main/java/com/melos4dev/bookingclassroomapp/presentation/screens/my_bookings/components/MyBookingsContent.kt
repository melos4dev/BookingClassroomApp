package com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.isDateBeforeYesterday

@Composable
fun MyBookingsContent(bookings: List<Booking>) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items = bookings.sortedByDescending { it.date }) { booking ->
            if (!isDateBeforeYesterday(booking.date!!)) {
                BookingCard(booking)
            }
        }

    }
}