package com.example.task_1_compose.ui.screens.todoslist

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.domain.di.ApiModule
import com.example.task_1_compose.TestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(ApiModule::class)
class TodosListViewTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun init() {
        hiltRule.inject()
        composeRule.setContent {
            TodosList()
        }
    }

    @Test
    fun addNewTodo_showNewTodo() {
    }
}