package com.melos4dev.bookingclassroomapp.domain.use_cases.users

import com.melos4dev.bookingclassroomapp.domain.model.User
import com.melos4dev.bookingclassroomapp.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.update(user)
}