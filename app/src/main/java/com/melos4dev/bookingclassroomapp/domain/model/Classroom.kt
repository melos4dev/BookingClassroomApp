package com.melos4dev.bookingclassroomapp.domain.model

data class Classroom(
    var classroomName: String = "",
    var floor: String = "",
    var zone: String = "",
    var classroomType: String = "",
    val monday: List<String> ?=null,
    var tuesday: List<String> ?=null,
    var wednesday: List<String> ?=null,
    var thursday: List<String> ?=null,
    var friday: List<String> ?=null,
)