package com.melos4dev.bookingclassroomapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.melos4dev.bookingclassroomapp.R
import com.melos4dev.bookingclassroomapp.presentation.navigation.AuthScreen
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(1500)
        navController.popBackStack()
        navController.navigate(AuthScreen.Login.route)
    }
    Splash()
}

@Composable
fun Splash() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkPrimaryColor),

        ) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_b_blanco),
                    contentDescription = "Logo splash"
                )
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "Booking Classroom",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }

        }

        Box(modifier = Modifier.fillMaxSize(), Alignment.BottomCenter) {

            Text(
                modifier = Modifier.padding(bottom = 32.dp),
                text = "Develop by Melos4Dev",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color.White
            )
        }

    }
}