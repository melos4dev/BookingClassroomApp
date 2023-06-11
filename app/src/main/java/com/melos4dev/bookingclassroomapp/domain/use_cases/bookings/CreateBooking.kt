package com.melos4dev.bookingclassroomapp.domain.use_cases.bookings

import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.domain.repository.BookingsRepository
import javax.inject.Inject

class CreateBooking @Inject constructor(private val repository: BookingsRepository) {

    suspend operator fun invoke(booking: Booking) =
        repository.create(booking)
}