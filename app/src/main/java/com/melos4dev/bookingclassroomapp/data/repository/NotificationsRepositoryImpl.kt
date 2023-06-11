package com.melos4dev.bookingclassroomapp.data.repository

import com.melos4dev.bookingclassroomapp.core.Constants.NOTIFICATIONS
import com.melos4dev.bookingclassroomapp.domain.model.Notification
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.repository.NotificationsRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class NotificationsRepositoryImpl @Inject constructor(
    @Named(NOTIFICATIONS) private val notificationsRef: CollectionReference
) : NotificationsRepository {
    override fun getNotifications(): Flow<Response<List<Notification>>> = callbackFlow {
        val snapshotListener = notificationsRef.addSnapshotListener { snapshot, e ->
            val notificationsResponse = if (snapshot != null) {
                val notifications = snapshot.toObjects(Notification::class.java)
                Response.Success(notifications)
            } else {
                Response.Failure(e)

            }
            trySend(notificationsResponse)

        }
        awaitClose {
            snapshotListener.remove()
        }

    }
}