package com.example.task_1_compose.ui.components.containers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import com.example.domain.resources.TestTags.REMOVE_FOCUS_CONTAINER_TEST_TAG

@Composable
fun RemoveFocusContainer(
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                focusManager.clearFocus()
            }
            .testTag(REMOVE_FOCUS_CONTAINER_TEST_TAG)
    ) { content() }
}
