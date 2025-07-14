package com.example.task_1_compose.ui.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.R
import com.example.domain.data.Comment
import com.example.domain.data.Post
import com.example.domain.repositories.PostsRepository
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import com.example.domain.utilities.ResourceProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PostScreenViewModel.PostScreenViewModelFactory::class)
class PostScreenViewModel @AssistedInject constructor(
    private val repository: PostsRepository,
    private val resourceProvider: ResourceProvider,
    @Assisted post: Post
) : ViewModel() {

    @AssistedFactory
    interface PostScreenViewModelFactory {
        fun create(post: Post): PostScreenViewModel
    }

    private var _post = MutableStateFlow(post)
    val post = _post.asStateFlow()

    private var _comments = MutableStateFlow<StatefulData<List<Comment>>>(LoadingData())
    val comments = _comments.asStateFlow()

    private var currentPage = 0

    init {
        viewModelScope.launch {
            loadComments()
        }
    }

    fun canLoadMoreComments(): Boolean {
        return _comments.value.canLoadMore(COMMENTS_PER_PAGE)
    }

    suspend fun loadComments() {
        if (!canLoadMoreComments()) {
            return
        }

        when (val newComments = repository.loadPostCommentsById(_post.value.id, currentPage)) {
            null -> _comments.value = ErrorData(
                resourceProvider.getStringResource(R.string.loading_comments_error)
            )
            else -> {
                val updatedComments = _post.value.comments + newComments
                _post.value = _post.value.copy(comments = updatedComments.toMutableList())
                currentPage++

                _comments.value = SuccessData(updatedComments)
            }
        }
    }

    fun toggleLike() {
        val currentIsLiked = _post.value.isLiked
        _post.value = _post.value.copy(isLiked = !currentIsLiked)
    }
}