package com.melos4dev.bookingclassroomapp.presentation.screens.my_bookings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.model.User
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.AuthUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.BookingsUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBookingsViewModel @Inject constructor(
    private val bookingsUseCases: BookingsUseCases,
    private val usersUseCases: UsersUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    //RESPONSE
    var bookingsResponse by mutableStateOf<Response<List<Booking>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    //USER
    private val getCurrentUser = authUseCases.getCurrentUser()
    var user = User()


    init {
        getMyBookings()
    }

    fun delete(idBooking :String) = viewModelScope.launch{
        deleteResponse= Response.Loading
        val result = bookingsUseCases.deleteBooking(idBooking)
        deleteResponse = result
    }
    private fun getMyBookings() = viewModelScope.launch {
        usersUseCases.getUserById(getCurrentUser!!.uid).collect() {
            user = it
            getBookings(user.email)
        }
    }

    fun getBookings(emailUser: String) = viewModelScope.launch {
        bookingsResponse = Response.Loading
        bookingsUseCases.getBookingsByEmailUser(emailUser).collect() { response ->
            bookingsResponse = response
        }

    }
}