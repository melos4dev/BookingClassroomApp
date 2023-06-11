package com.melos4dev.bookingclassroomapp.domain.use_cases.auth

import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.GetCurrentUser
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.Login
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.Logout
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.Signup

data class AuthUseCases(

    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val signup: Signup

)