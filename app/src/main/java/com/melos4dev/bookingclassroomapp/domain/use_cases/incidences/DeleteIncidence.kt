package com.melos4dev.bookingclassroomapp.domain.use_cases.incidences

import com.melos4dev.bookingclassroomapp.domain.repository.IncidencesRepository
import javax.inject.Inject

class DeleteIncidence @Inject constructor(private val repository: IncidencesRepository) {

    suspend operator fun invoke(idIncidence :String) =
        repository.delete(idIncidence)
}