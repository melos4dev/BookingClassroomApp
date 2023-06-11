package com.melos4dev.bookingclassroomapp.presentation.screens.my_incidences

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melos4dev.bookingclassroomapp.domain.model.Incidence
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.model.User
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.AuthUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.incidences.IncidencesUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyIncidencesViewModel @Inject constructor(
    private val incidencesUseCases: IncidencesUseCases,
    private val usersUseCases: UsersUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    //RESPONSE
    var incidencesResponse by mutableStateOf<Response<List<Incidence>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    //USER
    private val getCurrentUser = authUseCases.getCurrentUser()
    var user = User()


    init {
        getMyIncidences()


    }

    private fun getMyIncidences() = viewModelScope.launch {
        usersUseCases.getUserById(getCurrentUser!!.uid).collect() {
            user = it
            getIncidences(user.email)
        }

    }

    fun getIncidences(emailUser: String) = viewModelScope.launch {
        incidencesResponse = Response.Loading

        incidencesUseCases.getIncidencesByEmailUser(emailUser).collect() { response ->
            incidencesResponse = response
        }

    }

    fun delete(idIncidence :String) = viewModelScope.launch{
        deleteResponse= Response.Loading
        val result = incidencesUseCases.deleteIncidence(idIncidence)
        deleteResponse = result
    }
}