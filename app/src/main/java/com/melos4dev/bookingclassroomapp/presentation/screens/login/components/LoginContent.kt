package com.melos4dev.bookingclassroomapp.presentation.screens.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.melos4dev.bookingclassroomapp.R
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultButton
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultTextField
import com.melos4dev.bookingclassroomapp.presentation.screens.login.LoginViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.SecondaryTextColor


@Composable
fun LoginContent(viewModel: LoginViewModel = hiltViewModel()) {

   val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
            //      .background(DarkPrimaryColor),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(240.dp),
                    painter = painterResource(id = R.drawable.logo_horizontal_naranja),
                    contentDescription = "Logo Booking Classroom"
                )

            }

        }
        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 240.dp)
                .border(
                    BorderStroke(2.dp, color = DarkPrimaryColor),
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = DarkPrimaryColor
            )
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 40.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = SecondaryTextColor
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    text = "INICIAR SESIÓN",
                    onClick = {
                        viewModel.login()
                    },
                    enabled = viewModel.isEnabledLoginButton
                )

            }
        }
    }

}



