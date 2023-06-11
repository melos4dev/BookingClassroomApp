package com.melos4dev.bookingclassroomapp.domain.model

import java.util.Date


data class Notification(
    var subject: String = "",
    var message: String = "",
    var date: Date ?= null
)
