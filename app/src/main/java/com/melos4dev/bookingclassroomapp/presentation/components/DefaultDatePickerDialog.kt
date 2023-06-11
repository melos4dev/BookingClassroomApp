package com.melos4dev.bookingclassroomapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.graphics.Color
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.DarkPrimaryColor
import com.melos4dev.bookingclassroomapp.presentation.ui.theme.PrimaryTextColor
import com.melos4dev.bookingclassroomapp.presentation.utils.DateUtils.convertDateMillisToString

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultDatePickerDialog(
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirmOrCancel: (String) -> Unit,

    ) {

    val datePickerState = rememberDatePickerState()
    val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }

    if (openDialog) {

        DatePickerDialog(
            onDismissRequest = {
                onDismiss()
                onConfirmOrCancel(convertDateMillisToString(System.currentTimeMillis()))
            },
            colors = DatePickerDefaults.colors(containerColor = Color.White),
            confirmButton = {
                TextButton(
                    onClick = {
                        if (System.currentTimeMillis() > datePickerState.selectedDateMillis!!) {
                            onConfirmOrCancel(convertDateMillisToString(System.currentTimeMillis()))

                        } else {
                            onConfirmOrCancel(
                                convertDateMillisToString(
                                    datePickerState.selectedDateMillis?.toLong()
                                        ?: System.currentTimeMillis()
                                )
                            )
                        }
                        onDismiss()

                    },

                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                        onConfirmOrCancel(convertDateMillisToString(System.currentTimeMillis()))

                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(
                state = datePickerState, colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = DarkPrimaryColor,
                    titleContentColor = DarkPrimaryColor,
                    todayDateBorderColor = DarkPrimaryColor,
                    todayContentColor = PrimaryTextColor,
                    containerColor = Color.White,
                    selectedYearContainerColor = DarkPrimaryColor,
                    headlineContentColor = PrimaryTextColor,
                    subheadContentColor = DarkPrimaryColor,
                    weekdayContentColor = DarkPrimaryColor
                )
            )

        }
    }
}



