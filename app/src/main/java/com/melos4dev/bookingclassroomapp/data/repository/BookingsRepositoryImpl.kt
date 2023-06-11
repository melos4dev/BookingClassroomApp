package com.melos4dev.bookingclassroomapp.data.repository

import com.melos4dev.bookingclassroomapp.core.Constants.BOOKINGS
import com.melos4dev.bookingclassroomapp.domain.model.Booking
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.repository.BookingsRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class BookingsRepositoryImpl @Inject constructor(
    @Named(BOOKINGS) private val bookingsRef: CollectionReference
) : BookingsRepository {
    override suspend fun create(booking: Booking): Response<Boolean> {
        return try {

            //DATA
            bookingsRef.add(booking).await()

            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override fun getBookingsByUserEmail(emailUser: String): Flow<Response<List<Booking>>> =
        callbackFlow {
            val snapshotListener = bookingsRef.whereEqualTo("emailUser", emailUser)
                .addSnapshotListener { snapshot, e ->
                    val bookingsResponse = if (snapshot != null) {
                        val bookings = snapshot.toObjects(Booking::class.java)
                        snapshot.documents.forEachIndexed { index, documentSnapshot ->
                            bookings[index].id = documentSnapshot.id
                        }
                        Response.Success(bookings)
                    } else {
                        Response.Failure(e)

                    }
                    trySend(bookingsResponse)

                }
            awaitClose {
                snapshotListener.remove()
            }
        }

    override fun getBookings(): Flow<Response<List<Booking>>> = callbackFlow {
        val snapshotListener = bookingsRef.addSnapshotListener { snapshot, e ->
            val bookingsResponse = if (snapshot != null) {
                val bookings = snapshot.toObjects(Booking::class.java)
                snapshot.documents.forEachIndexed { index, documentSnapshot ->
                    bookings[index].id = documentSnapshot.id
                }
                Response.Success(bookings)
            } else {
                Response.Failure(e)

            }
            trySend(bookingsResponse)

        }
        awaitClose {
            snapshotListener.remove()
        }

    }
    override suspend fun delete(idBooking: String): Response<Boolean> {
        return try {
            bookingsRef.document(idBooking).delete().await()

            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}