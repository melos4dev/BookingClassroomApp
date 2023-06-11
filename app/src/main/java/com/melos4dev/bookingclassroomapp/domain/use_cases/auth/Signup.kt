package com.melos4dev.bookingclassroomapp.domain.use_cases.auth

import com.melos4dev.bookingclassroomapp.domain.model.User
import com.melos4dev.bookingclassroomapp.domain.repository.AuthRepository
import javax.inject.Inject

class Signup @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.signup(user)

}