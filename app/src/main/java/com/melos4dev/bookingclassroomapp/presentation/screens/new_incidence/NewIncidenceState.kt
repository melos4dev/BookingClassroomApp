package com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence

data class NewIncidenceState(
    val classroomName : String = "",
    val studentGroup : String = "",
    val description : String = "",
    var image: String = ""
)
