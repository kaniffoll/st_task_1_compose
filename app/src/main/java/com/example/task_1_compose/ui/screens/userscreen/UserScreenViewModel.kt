package com.example.task_1_compose.ui.screens.userscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.User
import com.example.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserScreenViewModel(user: User) : ViewModel() {
    private val usersRepository = UsersRepository()

    private val _user = MutableStateFlow(user)
    val user = _user.asStateFlow()

    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private var _loadingError = MutableStateFlow(false)
    val loadingError = _loadingError.asStateFlow()

    private var currentPage = 0

    private var _canLoadMore = MutableStateFlow(true)
    val canLoadMore = _canLoadMore.asStateFlow()

    init {
        loadComments()
    }

    fun loadComments() {
        if (!_canLoadMore.value) {
            return
        }
        viewModelScope.launch {
            _loadingError.value = false
            _isLoading.value = true
            val newComments = usersRepository.loadUserCommentsById(_user.value.id, currentPage)
            when (newComments) {
                null -> _loadingError.value = true
                mutableListOf<Comment>() -> _canLoadMore.value = false
                else -> {
                    val updatedComments = _user.value.comments + newComments
                    _user.value = _user.value.copy(comments = updatedComments.toMutableList())
                    currentPage++
                    _loadingError.value = false
                }
            }
            _isLoading.value = false
        }
    }
}