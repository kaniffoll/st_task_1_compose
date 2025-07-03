package com.example.task_1_compose.ui.screens.todoslist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.resources.TestTags.TODO_CARD
import com.example.domain.resources.TestTags.TODO_LAZY_COLUMN
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.SuccessData
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.cards.TodosCard
import com.example.task_1_compose.ui.components.containers.RemoveFocusContainer
import com.example.task_1_compose.ui.components.general.LoadingIndicator
import com.example.task_1_compose.ui.components.views.buttons.ErrorButton
import com.example.task_1_compose.ui.dialogs.DateAndTimePickerDialog
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodosList() {
    val viewModel: TodosListViewModel = hiltViewModel()

    val todos by viewModel.todos.collectAsState()

    val scope = rememberCoroutineScope()

    val lazyListState = rememberLazyListState()

    var showDialog by remember { mutableStateOf(false) }

    var lastAddedTitle by remember { mutableStateOf("") }

    RemoveFocusContainer {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .testTag(TODO_LAZY_COLUMN),
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(R.dimen.padding_medium_2)
                )
        ) {
            when (todos) {
                is LoadingData -> {
                    item {
                        LoadingIndicator()
                    }
                }

                is ErrorData -> {
                    item {
                        ErrorButton { viewModel.retryAction() }
                    }
                }

                is SuccessData -> {
                    val currentTodos = viewModel.currentTodos()
                    items(currentTodos.reversed(), key = { it.id }) { todo ->
                        TodosCard(
                            todo,
                            onTextChangeById = { id, text ->
                                viewModel.updateText(id, text)
                            },
                            onTodoTimePickerClicked = { text ->
                                lastAddedTitle = text
                                showDialog = true
                            },
                            scope = viewModel.viewModelScope,
                            modifier = Modifier.testTag(TODO_CARD)
                        ) { viewModel.removeTodoByIndex(todo.id) }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
            }
        }

        TodosFab {
            scope.launch {
                viewModel.addTodo()
                lazyListState.animateScrollToItem(0)
            }
        }

        if (showDialog) {
            val context = LocalContext.current
            DateAndTimePickerDialog(
                onDismiss = { showDialog = false },
                onConfirm = { triggerTime ->
                    showDialog = false
                    viewModel.createWorkRequest(context, triggerTime, lastAddedTitle)
                }
            )
        }
    }
}

@Composable
fun TodosFab(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                onClick()
            },
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(R.dimen.padding_medium_2),
                    end = dimensionResource(R.dimen.padding_medium_2)
                )
                .size(dimensionResource(R.dimen.fab_size)),
            containerColor = Color(0xFF00a3f9),
            contentColor = Color.White,
            shape = CircleShape

        ) {
            Text(
                text = stringResource(R.string.plus),
                fontSize = dimensionResource(R.dimen.text_large).value.sp,
                color = Color.White
            )
        }
    }
}
