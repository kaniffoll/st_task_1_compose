package com.example.task_1_compose.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User
import com.example.domain.repositories.UsersRepository
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.StringResources.LOADING_COMMENTS_ERROR
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import com.example.domain.statefuldata.canLoadMore
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = UserScreenViewModel.UserScreenViewModelFactory::class)
class UserScreenViewModel @AssistedInject constructor(
    private val repository: UsersRepository,
    @Assisted user: User
) : ViewModel() {

    @AssistedFactory
    interface UserScreenViewModelFactory {
        fun create(user: User): UserScreenViewModel
    }

    private val _user = MutableStateFlow(user)
    val user = _user.asStateFlow()

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

        when (val newComments = repository.loadUserCommentsById(_user.value.id, currentPage)) {
            null -> _comments.value = ErrorData(LOADING_COMMENTS_ERROR)
            else -> {
                val updatedComments = _user.value.comments + newComments
                _user.value = _user.value.copy(comments = updatedComments.toMutableList())
                currentPage++

                _comments.value = SuccessData(updatedComments)
            }
        }
    }

}