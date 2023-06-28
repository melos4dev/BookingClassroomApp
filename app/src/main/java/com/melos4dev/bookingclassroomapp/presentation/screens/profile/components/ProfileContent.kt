package com.melos4dev.bookingclassroomapp.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.melos4dev.bookingclassroomapp.presentation.components.DefaultButton
import com.melos4dev.bookingclassroomapp.presentation.navigation.DetailsScreen
import com.melos4dev.bookingclassroomapp.presentation.navigation.Graph
import com.melos4dev.bookingclassroomapp.presentation.screens.profile.ProfileViewModel
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.MediumPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryTextColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.SecondaryTextColor

@Composable
fun ProfileContent(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            Modifier
                .background(color = MediumPrimaryColor)
                .padding(top = 16.dp, bottom = 32.dp)
                .fillMaxWidth(),
            Alignment.Center
        ) {
            if (viewModel.userData.image != "") {
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    model = viewModel.userData.image,
                    contentDescription = "User Image",
                    contentScale = ContentScale.Crop
                )


            } else {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Account Box",
                    Modifier.size(120.dp),
                    tint = Color.White
                )
            }

        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = viewModel.userData.username,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryTextColor
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = viewModel.userData.email,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryTextColor,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.size(32.dp))
            DefaultButton(
                modifier = Modifier.width(240.dp),
                text = "MODIFICAR DATOS",
                onClick = {
                    navController.navigate(
                        (DetailsScreen.ProfileEdit.passUser(viewModel.userData.toJson()))
                    )
                },
                icon = Icons.Default.Edit
            )
            Spacer(modifier = Modifier.size(8.dp))
            DefaultButton(
                modifier = Modifier.width(240.dp),
                text = "CERRAR SESSIÓN",
                onClick = {
                    viewModel.logout()
                    navController.navigate(route = Graph.AUTHENTICATION) {
                        popUpTo(Graph.HOME) { inclusive = true }
                    }
                },
                icon = Icons.Default.Close,
                color = Color.Red
            )

        }

    }


}