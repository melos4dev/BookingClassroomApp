package com.melos4dev.bookingclassroomapp.domain.use_cases.bookings

import com.melos4dev.bookingclassroomapp.domain.repository.BookingsRepository
import javax.inject.Inject

class DeleteBooking @Inject constructor(private val repository: BookingsRepository) {

    suspend operator fun invoke(idBooking :String) =
        repository.delete(idBooking)
}