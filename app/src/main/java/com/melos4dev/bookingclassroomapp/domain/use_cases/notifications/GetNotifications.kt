package com.melos4dev.bookingclassroomapp.domain.use_cases.notifications

import com.melos4dev.bookingclassroomapp.domain.repository.NotificationsRepository
import javax.inject.Inject

class GetNotifications @Inject constructor(private val repository: NotificationsRepository){

    operator fun invoke () = repository.getNotifications()


}