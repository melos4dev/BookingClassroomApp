package com.melos4dev.bookingclassroomapp.domain.repository

import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser : FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun signup(user: User): Response<FirebaseUser>
    fun logout()
}