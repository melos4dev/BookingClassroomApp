package com.melos4dev.bookingclassroomapp.domain.model

import java.util.Date

data class Incidence(
    var id: String = "",
    var classroomName: String = "",
    var studentGroup: String = "",
    var description: String = "",
    var image: String = "",
    var emailUser : String = "",
    var date: Date? = null,
    var response : String = "",
    var isFixed: Boolean = false
)
