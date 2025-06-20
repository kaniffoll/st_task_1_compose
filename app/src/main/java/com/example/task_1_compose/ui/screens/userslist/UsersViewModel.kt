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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val usersRepository = UsersRepository()

    private var _users = MutableStateFlow<StatefulData<List<User>>>(LoadingData())
    val users = _users.asStateFlow()

    init {
        loadData()
    }

    fun currentUsers(): List<User> {
        return _users.value.unwrap(defaultValue = emptyList())
    }

    fun loadData() {
        viewModelScope.launch {
            when (val newUsers = usersRepository.loadUsers()) {
                null -> _users.value = ErrorData(LOADING_USERS_ERROR)
                else -> {
                    _users.value = SuccessData(newUsers)
                }
            }
        }
    }
}