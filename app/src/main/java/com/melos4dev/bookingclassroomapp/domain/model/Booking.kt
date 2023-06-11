package com.melos4dev.bookingclassroomapp.domain.model

import java.util.Date

data class Booking(
    var id: String ="",
    var classroomName: String = "",
    var studentGroup: String = "",
    var emailUser: String = "",
    var date: Date?= null,
    var hour: String = "",
    var zone: String = "",
    var floor: String = "",
    var classroomType: String = ""
)
