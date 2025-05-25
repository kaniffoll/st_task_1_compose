package com.example.task_1_compose.todos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.task_1_compose.R
import com.example.task_1_compose.components.cards.TodosCard
import com.example.task_1_compose.components.fabs.TodosFab

@Composable
fun TodosList() {
    val todos = remember { mutableStateListOf<String>() }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(R.dimen.padding_medium_2)
                )
        ) {
            items(todos.size) { index ->
                TodosCard(index, todos)
            }
        }
        TodosFab {
            todos.add("")
        }
    }
}

@Preview
@Composable
fun PreviewTodoList() {
    TodosList()
}