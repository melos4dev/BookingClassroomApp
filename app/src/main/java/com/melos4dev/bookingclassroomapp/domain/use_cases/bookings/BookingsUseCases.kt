package com.melos4dev.bookingclassroomapp.domain.use_cases.bookings

import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.CreateBooking
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.DeleteBooking
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.GetBookings
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.GetBookingsByEmailUser

data class BookingsUseCases(
    val create: CreateBooking,
    val getBookingsByEmailUser: GetBookingsByEmailUser,
    val getBookings: GetBookings,
    val deleteBooking: DeleteBooking
)