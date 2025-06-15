package com.example.task_1_compose.ui.screens.userslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.dataclasses.User
import com.example.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val usersRepository = UsersRepository()

    private var _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            usersRepository.fetchData()
            getUsersFromRepository()
        }
    }

    private fun getUsersFromRepository() {
        _users.value = usersRepository.getUsers()
    }

}