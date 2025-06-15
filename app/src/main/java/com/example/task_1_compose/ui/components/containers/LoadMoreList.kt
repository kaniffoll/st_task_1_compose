package com.example.task_1_compose.ui.components.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.task_1_compose.R

@Composable
fun LoadMoreList(
    canLoadMore: Boolean,
    onLoadMore: () -> Unit,
    contentSize: Int,
    itemContent: @Composable LazyItemScope.(index: Int) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val isAtBottom by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    var isLoadingMore by remember { mutableStateOf(false) }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom && canLoadMore) {
            isLoadingMore = true

            onLoadMore()

            isLoadingMore = false
        }
    }

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large))
    ) {
        items(contentSize, itemContent = itemContent)

        item {
            if (isLoadingMore) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(dimensionResource(R.dimen.loader_size))
                    )
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
