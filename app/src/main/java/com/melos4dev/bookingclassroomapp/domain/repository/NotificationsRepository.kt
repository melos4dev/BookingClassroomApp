package com.melos4dev.bookingclassroomapp.domain.repository

import com.melos4dev.bookingclassroomapp.domain.model.Notification
import com.melos4dev.bookingclassroomapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    fun getNotifications(): Flow<Response<List<Notification>>>
}