package com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melos4dev.bookingclassroomapp.domain.model.Response
import com.melos4dev.bookingclassroomapp.domain.model.User
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.UsersUseCases
import com.melos4dev.bookingclassroomapp.presentation.screens.profile_edit.ProfileEditState
import com.melos4dev.bookingclassroomapp.presentation.utils.ComposeFileProvider
import com.melos4dev.bookingclassroomapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // FORM STATE
    var state by mutableStateOf(ProfileEditState())
        private set

    // USERNAME
    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    // ENABLE BUTTON
    var isEnabledButton = false

    // ARGUMENTS
    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    // PROFILE_EDIT RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set

    // FILE
    var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()

    init {
        //SET ARGUMENTS
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    fun saveImage() = viewModelScope.launch {
      if (file != null) {
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
       }

        else{

            onUpdate(null)
        }

    }

    fun pickImage() = viewModelScope.launch {

        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }


    fun takePhoto() = viewModelScope.launch {

        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }


    fun onUpdate(url: String?) {
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url?: state.image
        )

        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result

    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    // Refactorizar
    fun enabledButton() {
        isEnabledButton = isUsernameValid
    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg = ""
        } else {
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }
        enabledButton()
    }

}