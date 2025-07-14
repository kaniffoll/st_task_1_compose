package com.example.task_1_compose.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.views.buttons.TextButton
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar

enum class PickerStep {
    DATE,
    TIME
}

@Composable
fun DateAndTimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (LocalDateTime) -> Unit
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    var step by remember { mutableStateOf(PickerStep.DATE) }

    when (step) {
        PickerStep.DATE -> DatePickerDialog(
            onDismiss = {
                onDismiss()
            },
            onConfirm = {
                selectedDate = it
                step = PickerStep.TIME
            }
        )

        PickerStep.TIME -> TimePickerDialog(
            selectedDate = selectedDate,
            onDismiss = {
                onDismiss()
            },
            onConfirm = onConfirm
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (LocalDate) -> Unit
) {
    val currentTime = Calendar.getInstance()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = currentTime.timeInMillis
    )

    val selectedDateMillis = datePickerState.selectedDateMillis
    val selectedDate = if (selectedDateMillis != null) {
        Instant
            .ofEpochMilli(selectedDateMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } else {
        LocalDate.now()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(text = stringResource(R.string.dismiss)) {
                onDismiss()
            }
        },
        confirmButton = {
            TextButton(text = stringResource(R.string.ok)) {
                onConfirm(selectedDate)
            }
        },
        text = { DatePicker(datePickerState) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    selectedDate: LocalDate,
    onDismiss: () -> Unit,
    onConfirm: (LocalDateTime) -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    val pickedHour = timePickerState.hour
    val pickedMinute = timePickerState.minute

    val triggerTime = selectedDate.atTime(pickedHour, pickedMinute, 0)

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(text = stringResource(R.string.dismiss)) {
                onDismiss()
            }
        },
        confirmButton = {
            TextButton(text = stringResource(R.string.ok)) {
                onConfirm(triggerTime)
            }
        },
        text = { TimePicker(timePickerState) }
    )
}