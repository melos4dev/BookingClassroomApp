package com.melos4dev.bookingclassroomapp.presentation.screens.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melos4dev.bookingclassroomapp.domain.model.Notification
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.use_cases.notifications.NotificationsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val notificationsUseCases: NotificationsUseCases
): ViewModel() {

    //RESPONSE
    var notificationsResponse by mutableStateOf<Response<List<Notification>>?>(null)

    init{
        getNotifications()
    }

    fun getNotifications()= viewModelScope.launch {
        notificationsResponse = Response.Loading

        notificationsUseCases.getNotifications().collect(){response ->
            notificationsResponse = response
        }

    }
}