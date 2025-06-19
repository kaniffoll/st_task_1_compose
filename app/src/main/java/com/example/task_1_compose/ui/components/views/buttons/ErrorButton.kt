package com.example.task_1_compose.ui.components.views.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.runBlocking

@Composable
fun ErrorButton(onRetry: suspend () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            text = "Loading Error, try again",
            color = Color.Red
        ) {
            runBlocking {
                onRetry()
            }
        }
    }
}