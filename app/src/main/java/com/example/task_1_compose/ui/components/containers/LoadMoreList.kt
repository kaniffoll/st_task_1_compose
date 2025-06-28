package com.example.task_1_compose.ui.components.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.example.domain.resources.TestTags.LOAD_MORE_LIST
import com.example.task_1_compose.R
import com.example.task_1_compose.ui.components.general.LoadingIndicator
import com.example.task_1_compose.ui.components.views.buttons.ErrorButton
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun <T> LoadMoreList(
    data: StatefulData<List<T>>,
    onLoadMore: suspend () -> Unit,
    isPaginationFinished: () -> Boolean,
    scope: CoroutineScope,
    itemContent: @Composable LazyItemScope.(index: Int) -> Unit
) {
    var isPaginationInProgress by remember { mutableStateOf(false) }

    val lazyListState = rememberLazyListState()

    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom && !isPaginationFinished() && !isPaginationInProgress) {
            isPaginationInProgress = true
            scope.launch {
                onLoadMore()
                isPaginationInProgress = false
            }
        }
    }

    LazyColumn(
        modifier = Modifier.testTag(LOAD_MORE_LIST),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
    ) {
        when (data) {
            is LoadingData -> {
                item {
                    LoadingIndicator()
                }
            }
            is ErrorData -> {
                item {
                    ErrorButton { onLoadMore() }
                }
            }
            is SuccessData -> {
                items(data.result.size, itemContent = itemContent)
                if (isPaginationInProgress) {
                    item {
                       LoadingIndicator()
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.padding_large)
                )
            )
        }
    }
}
