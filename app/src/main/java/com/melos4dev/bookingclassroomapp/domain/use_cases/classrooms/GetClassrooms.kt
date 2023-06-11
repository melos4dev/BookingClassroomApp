package com.melos4dev.bookingclassroomapp.domain.use_cases.classrooms

import com.melos4dev.bookingclassroomapp.domain.repository.ClassroomsRepository
import javax.inject.Inject

class GetClassrooms @Inject constructor(private val repository: ClassroomsRepository){

    operator fun invoke () = repository.getClassrooms()

}