package com.melos4dev.bookingclassroomapp.domain.use_cases.incidences

import com.melos4dev.bookingclassroomapp.domain.model.Incidence
import com.melos4dev.bookingclassroomapp.domain.repository.IncidencesRepository
import java.io.File
import javax.inject.Inject

class CreateIncidence @Inject constructor(private val repository: IncidencesRepository) {

    suspend operator fun invoke(incidence: Incidence, file: File?) =
        repository.create(incidence, file)
}