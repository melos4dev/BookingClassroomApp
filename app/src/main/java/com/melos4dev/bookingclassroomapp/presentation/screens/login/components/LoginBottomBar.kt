package com.melos4dev.bookingclassroomapp.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.presentation.navigation.AuthScreen
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.SecondaryTextColor


@Composable
fun LoginBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿No tienes cuenta?",
            fontSize = 14.sp,
            color = SecondaryTextColor
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AuthScreen.Signup.route)
            },
            text = "REGÍSTRATE AQUÍ",
            color = DarkPrimaryColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
