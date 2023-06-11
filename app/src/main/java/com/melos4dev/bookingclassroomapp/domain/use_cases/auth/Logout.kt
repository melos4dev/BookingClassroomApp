package com.melos4dev.bookingclassroomapp.domain.use_cases.auth

import com.melos4dev.bookingclassroomapp.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.logout()

}