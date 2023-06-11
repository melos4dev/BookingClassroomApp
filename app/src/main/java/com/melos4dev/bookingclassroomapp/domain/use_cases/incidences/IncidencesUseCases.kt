package com.melos4dev.bookingclassroomapp.domain.use_cases.incidences



data class IncidencesUseCases(
    val create: CreateIncidence,
    val getIncidencesByEmailUser: GetIncidencesByEmailUser,
    val deleteIncidence: DeleteIncidence
)