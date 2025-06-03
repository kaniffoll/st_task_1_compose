package com.example.task_1_compose.viewmodels

import androidx.lifecycle.ViewModel
import com.example.task_1_compose.data.dataclasses.User
import com.example.task_1_compose.repositories.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel(usersRepository: UsersRepository): ViewModel() {
    private var _usersState = MutableStateFlow<List<User>>(emptyList())
    val usersState = _usersState.asStateFlow()

    init {
        _usersState.value = usersRepository.getUsers()
    }
}