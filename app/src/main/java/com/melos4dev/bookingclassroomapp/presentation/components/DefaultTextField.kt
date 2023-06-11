package com.melos4dev.bookingclassroomapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryTextColor


@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    errorMsg: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,

    ) {

    Column() {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = {
                Text(label)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = PrimaryColor

                )
            },
            visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DarkPrimaryColor,
                unfocusedBorderColor = PrimaryColor,
                focusedLabelColor = DarkPrimaryColor,
                disabledBorderColor = PrimaryColor,
                disabledLabelColor = PrimaryColor,
                disabledTextColor = PrimaryTextColor,
                unfocusedLabelColor = DarkPrimaryColor


            ),

            enabled = enabled,
            readOnly = readOnly

        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = errorMsg,
            fontSize = 10.sp,
            color = Color.Red
        )
    }


}