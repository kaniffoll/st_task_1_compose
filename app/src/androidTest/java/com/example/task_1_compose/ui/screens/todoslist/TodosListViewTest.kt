package com.example.task_1_compose.ui.screens.todoslist

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.di.ApiModule
import com.example.domain.resources.TestTags.REMOVE_FOCUS_CONTAINER
import com.example.domain.resources.TestTags.TODO_CARD
import com.example.domain.resources.TestTags.TODO_CARD_TEXT_FIELD
import com.example.task_1_compose.R
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

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val testText = "TEST"
    private val testRepeatCount = 5
    private val plusText = context.getString(R.string.plus)
    private val doneIconDescription = context.getString(R.string.done_icon)
    private val updateIconDescription = context.getString(R.string.update_icon)

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            TodosList()
        }
    }

    @Test
    fun addNewTodo_showNewTodo() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithTag(TODO_CARD).assertIsDisplayed()
    }

    @Test
    fun deleteTodo() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithContentDescription(doneIconDescription).performClick()
        composeRule.onNodeWithTag(TODO_CARD).assertDoesNotExist()
    }

    @Test
    fun addText() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).performTextInput(testText)
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).assertTextContains(testText)
    }

    @Test
    fun checkIconChangeWhenFocus() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithContentDescription(doneIconDescription).assertExists()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithContentDescription(updateIconDescription).assertExists()
    }

    @Test
    fun updateTextByButton() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).performTextInput(testText)
        composeRule.onNodeWithContentDescription(updateIconDescription).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).assertTextContains(testText)
    }

    @Test
    fun changeText() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).performTextInput(testText)
        composeRule.onNodeWithTag(REMOVE_FOCUS_CONTAINER).performClick()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).performTextInput(testText)
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).assertTextContains(testText + testText)
    }

    @Test
    fun checkTodoTextFieldFocused() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).assertIsFocused()
    }

    @Test
    fun checkTodoTextFieldUnfocused() {
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onNodeWithTag(TODO_CARD).performClick()
        composeRule.onNodeWithTag(REMOVE_FOCUS_CONTAINER).performClick()
        composeRule.onNodeWithTag(TODO_CARD_TEXT_FIELD).assertIsNotFocused()
    }

    @Test
    fun checkTodoCount() {
        repeat(testRepeatCount) {
            composeRule.onNodeWithText(plusText).performClick()
        }
        composeRule.onAllNodesWithTag(TODO_CARD).assertCountEquals(testRepeatCount)
    }

    @Test
    fun checkNoTodoAfterDeletingAll() {
        repeat(testRepeatCount) {
            composeRule.onNodeWithText(plusText).performClick()
        }
        repeat(testRepeatCount) {
            composeRule.onAllNodesWithContentDescription(doneIconDescription)[0].performClick()
        }
        composeRule.onNodeWithTag(TODO_CARD).assertDoesNotExist()
    }

    @Test
    fun addingTodo_scrollsToTop() {
        val todosToAdd = 10
        repeat(todosToAdd) {
            composeRule.onNodeWithText(plusText).performClick()
        }
        composeRule.onNodeWithText(plusText).performClick()
        composeRule.onAllNodesWithTag(TODO_CARD)[0].assertIsDisplayed()
    }
}