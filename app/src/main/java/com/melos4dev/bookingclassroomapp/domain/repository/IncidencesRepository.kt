package com.melos4dev.bookingclassroomapp.domain.repository

import com.melos4dev.bookingclassroomapp.domain.model.Incidence
import com.melos4dev.bookingclassroomapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IncidencesRepository {

    suspend fun create(incidence: Incidence, file: File?): Response<Boolean>
    fun getIncidencesByUserEmail(emailUser:String): Flow<Response<List<Incidence>>>
    suspend fun delete(idIncidence:String):Response<Boolean>
}