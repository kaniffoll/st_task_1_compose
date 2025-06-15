package com.example.task_1_compose.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.views.buttons.TextButton

@Composable
fun LoadingAndError(
    isLoading: Boolean,
    loadingError: Boolean,
    onRetry: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(dimensionResource(R.dimen.loader_size))
                )
            }
        }
        if (loadingError) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TextButton(
                    text = "Loading Error, try again",
                    color = Color.Red
                ) { onRetry() }
            }
        }
    }
}