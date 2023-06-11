package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.domain.model.Classroom
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.model.User
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.AuthUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.BookingsUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.classrooms.ClassroomsUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.UsersUseCases
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.getDayOfWeek
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.convertDateToString
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.convertStringToDate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewBookingViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bookingsUseCases: BookingsUseCases,
    private val usersUseCases: UsersUseCases,
    private val classroomsUseCases: ClassroomsUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    // FORM STATE
    var state by mutableStateOf(NewBookingState())
        private set

    // STUDENT GROUP
    var isStudentGroupValid by mutableStateOf(false)
        private set
    var studentGroupErrMsg by mutableStateOf("")
        private set

    //RESPONSE
    var createBookingResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var getBookingsResponse by mutableStateOf<Response<List<Booking>>?>(null)
        private set
    var getClassroomsResponse by mutableStateOf<Response<List<Classroom>>?>(null)
        private set

    //USER
    private val getCurrentUser = authUseCases.getCurrentUser()
    var user = User()

    // LISTS
    var listOfExistingBookings = listOf<Booking>()
    var listOfClassrooms = listOf<Classroom>()
    var listOfAvailableClassrooms: MutableList<Classroom> = mutableListOf()

    val listClassroomType = listOf("Aula ordenadores", "Carro ordenadores", "Aula simple")
    val listHour = listOf("1ª", "2ª", "3ª", "4ª", "5ª", "6ª")


    init {
        getUserById()
        getBookings()
        getClassrooms()
    }

    private fun getClassrooms() = viewModelScope.launch {

        getClassroomsResponse = Response.Loading
        classroomsUseCases.getClassrooms().collect() { response ->
            getClassroomsResponse = response
        }
    }

    fun getBookings() = viewModelScope.launch {

        getBookingsResponse = Response.Loading
        bookingsUseCases.getBookings().collect() { response ->
            getBookingsResponse = response
        }

    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(getCurrentUser!!.uid).collect() {
            user = it
        }
    }

    fun getAvailableClassrooms() {

        listOfClassrooms.forEach { classroom ->

            if (state.classroomType == classroom.classroomType) {

                when (getDayOfWeek(state.date)) {
                    "monday" ->
                        for (hour in classroom.monday!!) {
                            if (hour == state.hour) {
                                listOfAvailableClassrooms.add(classroom)
                            }
                        }
                    "tuesday" ->
                        for (hour in classroom.tuesday!!) {
                            if (hour == state.hour) {
                                listOfAvailableClassrooms.add(classroom)
                            }
                        }
                    "wednesday" ->
                        for (hour in classroom.wednesday!!) {
                            if (hour == state.hour) {
                                listOfAvailableClassrooms.add(classroom)
                            }
                        }
                    "thursday" ->
                        for (hour in classroom.thursday!!) {
                            if (hour == state.hour) {
                                listOfAvailableClassrooms.add(classroom)
                            }
                        }
                    "friday" ->
                        for (hour in classroom.friday!!) {
                            if (hour == state.hour) {
                                listOfAvailableClassrooms.add(classroom)
                            }
                        }
                }

               listOfExistingBookings.forEach { booking ->
                    if (booking.classroomName == classroom.classroomName && convertDateToString(
                            booking.date!!
                        ) ==
                        state.date && booking.hour == state.hour
                    ) {
                        listOfAvailableClassrooms.remove(classroom)
                    }
                }
            }
        }
    }




    fun createBooking(booking: Booking) = viewModelScope.launch {

        createBookingResponse = Response.Loading
        val result = bookingsUseCases.create(booking)
        createBookingResponse = result

    }

    fun onNewBooking(classroom: Classroom) {
        val booking = Booking(
            classroomName = classroom.classroomName,
            studentGroup = state.studentGroup,
            hour = state.hour,
            emailUser = user.email,
            date = convertStringToDate(state.date),
            zone = classroom.zone,
            floor = classroom.floor,
            classroomType = state.classroomType
        )
        createBooking(booking)
    }

    fun clearForm() {
        state = state.copy(
            classroomName = "",
            studentGroup = "",
            hour = "",
            emailUser = "",
            date = "",
            zone = "",
            floor = "",
            classroomType = ""
        )
        createBookingResponse = null
    }


    fun onStudentGroupInput(studentGroup: String) {
        state = state.copy(studentGroup = studentGroup)

    }

    fun validateStudentGroup() {
        if (state.studentGroup.length >= 3) {
            isStudentGroupValid = true
            studentGroupErrMsg = ""
        } else {
            isStudentGroupValid = false
            studentGroupErrMsg = "Introduzca un grupo válido"
        }

    }

    fun validateInputs(): Boolean{

        return isStudentGroupValid && (state.hour != "") &&
                (state.date !="") && (state.classroomType != "")
    }


}


