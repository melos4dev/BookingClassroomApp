package com.melos4dev.bookingclassroomapp.data.repository

import com.melos4dev.bookingclassroomapp.core.Constants.CLASSROOMS
import com.melos4dev.bookingclassroomapp.domain.model.Classroom
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.repository.ClassroomsRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class ClassroomsRepositoryImpl @Inject constructor(
    @Named(CLASSROOMS) private val classroomsRef: CollectionReference
) : ClassroomsRepository {
    override fun getClassrooms(): Flow<Response<List<Classroom>>> = callbackFlow {
        val snapshotListener = classroomsRef.addSnapshotListener { snapshot, e ->
            val classroomsResponse = if (snapshot != null) {
                val classrooms = snapshot.toObjects(Classroom::class.java)
                Response.Success(classrooms)
            } else {
                Response.Failure(e)

            }
            trySend(classroomsResponse)

        }
        awaitClose {
            snapshotListener.remove()
        }

    }
}