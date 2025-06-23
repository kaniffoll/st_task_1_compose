package com.example.task_1_compose.ui.screens.userslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.User
import com.example.domain.repositories.UsersRepository
import com.example.domain.resources.StringResources.LOADING_USERS_ERROR
import com.example.domain.statefuldata.ErrorData
import com.example.domain.statefuldata.LoadingData
import com.example.domain.statefuldata.StatefulData
import com.example.domain.statefuldata.SuccessData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    private var _users = MutableStateFlow<StatefulData<List<User>>>(LoadingData())
    val users = _users.asStateFlow()

    init {
        viewModelScope.launch {
            loadUsers()
        }
    }

    fun currentUsers(): List<User> {
        return _users.value.unwrap(defaultValue = emptyList())
    }

    suspend fun loadUsers() {
        when (val newUsers = repository.loadUsers()) {
            null -> _users.value = ErrorData(LOADING_USERS_ERROR)
            else -> {
                _users.value = SuccessData(newUsers)
            }
        }
    }
}