package com.melos4dev.bookingclassroomapp.domain.repository

import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
    fun getUserById(id:String): Flow<User>
    suspend fun update(user: User): Response<Boolean>
    suspend fun saveImage(file: File): Response<String>
}