package com.melos4dev.bookingclassroomapp.data.repository

import android.net.Uri
import com.melos4dev.bookingclassroomapp.core.Constants.INCIDENCES
import com.melos4dev.bookingclassroomapp.domain.model.Incidence
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.repository.IncidencesRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class IncidencesRepositoryImpl @Inject constructor(
    @Named(INCIDENCES) private val incidencesRef: CollectionReference,
    @Named(INCIDENCES) private val storageIncidencesRef: StorageReference,
) : IncidencesRepository {
    override suspend fun create(incidence: Incidence, file: File?): Response<Boolean> {
        return try {

            //IMAGE

            if (file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storageIncidencesRef.child(file.name)
                val uploadTask = ref.putFile(fromFile)
                // Esperar a que la carga de la imagen se complete exitosamente
                uploadTask.addOnSuccessListener {
                    // Obtener la URL de descarga de la imagen
                    ref.downloadUrl.addOnSuccessListener { url ->
                        // Imagen creada exitosamente
                        incidence.image = url.toString()
                        // Continuar con la creación de la incidencia
                        incidencesRef.add(incidence).addOnSuccessListener {
                        // Incidencia creada exitosamente
                        }
                    }
                }
            } else {
                incidence.image = ""
                // Continuar con la creación de la incidencia
                incidencesRef.add(incidence).addOnSuccessListener {
                    // Incidencia creada exitosamente
                }
            }


            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override fun getIncidencesByUserEmail(emailUser: String): Flow<Response<List<Incidence>>> =
        callbackFlow {
            val snapshotListener = incidencesRef.whereEqualTo("emailUser", emailUser)
                .addSnapshotListener { snapshot, e ->
                    val incidencesResponse = if (snapshot != null) {
                        val incidences = snapshot.toObjects(Incidence::class.java)
                        snapshot.documents.forEachIndexed { index, documentSnapshot ->
                            incidences[index].id = documentSnapshot.id
                        }
                        Response.Success(incidences)
                    } else {
                        Response.Failure(e)

                    }
                    trySend(incidencesResponse)

                }
            awaitClose {
                snapshotListener.remove()
            }
        }

    override suspend fun delete(idIncidence: String): Response<Boolean> {
        return try {
            incidencesRef.document(idIncidence).delete().await()

            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}