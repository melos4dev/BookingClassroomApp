package com.melos4dev.bookingclassroomapp.domain.repository

import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface BookingsRepository {
    suspend fun create(booking: Booking): Response<Boolean>
    fun getBookingsByUserEmail(emailUser:String): Flow<Response<List<Booking>>>
    fun getBookings(): Flow<Response<List<Booking>>>
    suspend fun delete(idBooking:String): Response<Boolean>
}