package com.melos4dev.bookingclassroomapp.domain.use_cases.bookings

import com.melos4dev.bookingclassroomapp.domain.repository.BookingsRepository
import javax.inject.Inject

class GetBookingsByEmailUser @Inject constructor(private val repository: BookingsRepository) {

    operator fun invoke (emailUser: String) =  repository.getBookingsByUserEmail(emailUser)
}