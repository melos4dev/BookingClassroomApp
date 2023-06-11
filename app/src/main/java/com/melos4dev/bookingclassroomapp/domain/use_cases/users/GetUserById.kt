package com.melos4dev.bookingclassroomapp.domain.use_cases.users

import com.melos4dev.bookingclassroomapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(id: String)= repository.getUserById(id)
}