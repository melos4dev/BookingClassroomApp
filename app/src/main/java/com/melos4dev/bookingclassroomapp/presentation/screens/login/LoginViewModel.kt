package com.melos4dev.bookingclassroomapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import com.melos4dev.bookingclassroomapp.presentation.screens.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    //STATES
    var state by mutableStateOf(LoginState())
        private set

    // EMAIL VALIDATION
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    // PASSWORD VALIDATION
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    // ENABLE BUTTON
    var isEnabledLoginButton = false

    // LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)


    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null) { // SESION INICIADA
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        // ES UN EMAIL VALIDO
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        } else {
            isEmailValid = false
            emailErrMsg = "El email no es válido"
        }

        enabledLoginButton()
    }

    fun validatePassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrMsg = ""
        } else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 carácteres"
        }

        enabledLoginButton()
    }


}