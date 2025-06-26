package com.example.task_1_compose.ui.screens.postslist

import androidx.lifecycle.ViewModel
import com.example.domain.data.dataclasses.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.resources.StringResources.LOADING_POSTS_ERROR
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {
    private var _posts = MutableStateFlow<StatefulData<List<Post>>>(LoadingData())
    val posts = _posts.asStateFlow()

    private var currentPage = 0

    fun toggleLike(id: Int) {
        if (_posts.value is SuccessData) {
            val currentState = _posts.value as SuccessData

            _posts.value = SuccessData(
                result = currentState.result.map {
                    if (it.id == id) it.copy(isLiked = !it.isLiked) else it
                }
            )
        }
    }

    fun canLoadMorePosts(): Boolean {
        return _posts.value.canLoadMore(POSTS_PER_PAGE)
    }

    fun currentPosts(): List<Post> {
        return _posts.value.unwrap(defaultValue = emptyList())
    }

    suspend fun loadNextPosts() {
        if (!canLoadMorePosts()) {
            return
        }

        when (val newPosts = repository.loadNextPosts(currentPage)) {
            null -> _posts.value = ErrorData(LOADING_POSTS_ERROR)
            else -> {
                currentPage++
                _posts.value = SuccessData(newPosts)
            }
        }
    }
}