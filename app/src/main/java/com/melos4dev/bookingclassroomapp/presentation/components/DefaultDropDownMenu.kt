package com.melos4dev.bookingclassroomapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryTextColor

@Composable
fun DefaultDropDownMenu(
    listItems: List<String>,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,

    ) {
    var selectedItem by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column() {

        OutlinedTextField(
            value = selectedItem,
            onValueChange = {
                onValueChange(it)
            },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DarkPrimaryColor,
                unfocusedBorderColor = PrimaryColor,
                focusedLabelColor = DarkPrimaryColor,
                disabledBorderColor = PrimaryColor,
                disabledLabelColor = DarkPrimaryColor,
                disabledTextColor = PrimaryTextColor,

                ),
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = PrimaryColor

                )
            },
            label = { Text(text = label) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    tint = PrimaryColor
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
        ) {
            listItems.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onValueChange(item)
                        selectedItem = item
                    }, text = {
                        Text(text = item)
                    })
            }
        }
    }
}