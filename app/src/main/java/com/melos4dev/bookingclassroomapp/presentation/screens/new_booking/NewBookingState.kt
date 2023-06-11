package com.melos4dev.bookingclassroomapp.presentation.screens.new_booking

import java.util.Date

data class NewBookingState(
    var classroomName: String = "",
    var studentGroup: String = "",
    var emailUser: String = "",
    var date: String = "",
    var hour: String = "",
    var zone: String = "",
    var floor: String = "",
    var classroomType: String = ""
)
