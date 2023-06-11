package com.melos4dev.bookingclassroomapp.domain.use_cases.users

import com.melos4dev.bookingclassroomapp.domain.use_cases.users.Create
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.GetUserById
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.SaveImage
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.Update

data class UsersUseCases(
    val create: Create,
    val getUserById: GetUserById,
    val update : Update,
    val saveImage: SaveImage
)
