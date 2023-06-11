package com.melos4dev.bookingclassroomapp.domain.use_cases.incidences

import com.melos4dev.bookingclassroomapp.domain.repository.IncidencesRepository
import javax.inject.Inject

class GetIncidencesByEmailUser @Inject constructor(private val repository: IncidencesRepository) {

     operator fun invoke (emailUser: String) =  repository.getIncidencesByUserEmail(emailUser)
}