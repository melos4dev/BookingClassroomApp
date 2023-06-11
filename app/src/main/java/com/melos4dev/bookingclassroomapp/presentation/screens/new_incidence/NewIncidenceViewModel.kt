package com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence

import android.content.Context
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
import com.melos4dev.bookingclassroomapp.presentation.screens.new_incidence.NewIncidenceState
import com.melos4dev.bookingclassroomapp.presentation.utils.ComposeFileProvider
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.getCurrentDate
import com.melos4dev.bookingclassroomapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewIncidenceViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val incidencesUseCases: IncidencesUseCases,
    private val usersUseCases: UsersUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    // FORM STATE
    var state by mutableStateOf(NewIncidenceState())
        private set

    // CLASSROOM NAME
    var isClassroomNameValid by mutableStateOf(false)
        private set
    var classroomNameErrMsg by mutableStateOf("")
        private set

    // STUDENT GROUP
    var isStudentGroupValid by mutableStateOf(false)
        private set
    var studentGroupErrMsg by mutableStateOf("")
        private set

    // DESCRIPTION
    var isDescriptionValid by mutableStateOf(false)
        private set
    var descriptionErrMsg by mutableStateOf("")
        private set

    // ENABLE BUTTON
    var isEnabledLoginButton = false
        private set

    // FILE
    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    //RESPONSE
    var createIncidenceResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //USER
    private val getCurrentUser = authUseCases.getCurrentUser()
    var user = User()


    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(getCurrentUser!!.uid).collect() {
            user = it
        }
    }

    fun createIncidence(incidence: Incidence) = viewModelScope.launch {

        createIncidenceResponse = Response.Loading
        val result = incidencesUseCases.create(incidence, file)
        createIncidenceResponse = result

    }

    fun onNewIncidence() {
        val incidence = Incidence(
            classroomName = state.classroomName,
            studentGroup = state.studentGroup,
            description = state.description,
         //   image = state.image,
            emailUser = user.email,
            date = getCurrentDate()
        )

        createIncidence(incidence)
    }



    fun clearForm() {
        state = state.copy(
            classroomName = "",
            studentGroup = "",
            description = "",
            image = ""
        )
        createIncidenceResponse = null
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


    fun onClassroomNameInput(classroomName: String) {
        state = state.copy(classroomName = classroomName)
    }

    fun onStudentGroupInput(studentGroup: String) {
        state = state.copy(studentGroup = studentGroup)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }

    fun enabledLoginButton() {
        isEnabledLoginButton =
            isClassroomNameValid &&
                    isStudentGroupValid &&
                    isDescriptionValid
    }

    fun validateClassroomName() {
        if (state.classroomName.length >= 3) {
            isClassroomNameValid = true
            classroomNameErrMsg = ""
        } else {
            isClassroomNameValid = false
            classroomNameErrMsg = "Introduzca un nombre de aula válido"
        }
        enabledLoginButton()
    }

    fun validateStudentGroup() {
        if (state.studentGroup.length >= 3) {
            isStudentGroupValid = true
            studentGroupErrMsg = ""
        } else {
            isStudentGroupValid = false
            studentGroupErrMsg = "Introduzca un grupo válido"
        }
        enabledLoginButton()
    }

    fun validateDescription() {
        if (state.description.length >= 5) {
            isDescriptionValid = true
            descriptionErrMsg = ""
        } else {
            isDescriptionValid = false
            descriptionErrMsg = "Introduzca un nombre de aula válido"
        }
        enabledLoginButton()
    }

}