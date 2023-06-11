package com.melos4dev.bookingclassroomapp.domain.repository

import com.melos4dev.bookingclassroomapp.domain.model.Classroom
import com.melos4dev.bookingclassroomapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ClassroomsRepository {
    fun getClassrooms(): Flow<Response<List<Classroom>>>
}