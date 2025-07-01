package com.example.task_1_compose.ui.screens.user

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToNode
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.data.dataclasses.Address
import com.example.domain.data.dataclasses.User
import com.example.domain.di.ApiModule
import com.example.domain.resources.AppSettings.COMMENTS_PER_PAGE
import com.example.domain.resources.AppSettings.INITIAL_COMMENTS_COUNT
import com.example.domain.resources.TestTags.COMMENTS_SECTION
import com.example.domain.resources.TestTags.COMMENT_CARD
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
class UserScreenViewTest {
    private val mockUser = User(
        id = 0,
        name = "User 0",
        username = "_username",
        address = Address(
            "", "", "",
        ),
        phone = ""
    )

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val showMoreButtonText = context.getString(R.string.show_more)
    private val showLessButtonText = context.getString(R.string.show_less)
    private val loadMoreButtonText = context.getString(R.string.load_more)

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestActivity>()

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(
                    targetState = mockUser, label = ""
                ) { user ->
                    UserScreen(
                        user = user,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this
                    )
                }
            }
        }
    }

    @Test
    fun clickShowMoreButton_displayMoreComments() {
        composeRule.onAllNodesWithTag(COMMENT_CARD).assertCountEquals(INITIAL_COMMENTS_COUNT)
        composeRule.onNodeWithText(showMoreButtonText).performClick()
        for (i in 0..<COMMENTS_PER_PAGE) {
            composeRule.onAllNodesWithTag(COMMENT_CARD)[i].assertExists()
            composeRule.onNodeWithTag(COMMENTS_SECTION).performScrollToIndex(i)
        }
    }

    @Test
    fun clickShowLessButton_displayLessComments() {
        composeRule.onNodeWithText(showMoreButtonText).performClick()
        composeRule.onNodeWithTag(COMMENTS_SECTION).performScrollToNode(
            hasText(showLessButtonText)
        )
        composeRule.onNodeWithText(showLessButtonText).performClick()
        composeRule.onAllNodesWithTag(COMMENT_CARD).assertCountEquals(INITIAL_COMMENTS_COUNT)
    }

    @Test
    fun loadMoreComments_displayLoadMoreButton() {
        composeRule.onNodeWithText(showMoreButtonText).performClick()
        composeRule.onNodeWithTag(COMMENTS_SECTION).performScrollToNode(
            hasText(loadMoreButtonText)
        )
    }

    @Test
    fun clickLoadMoreComments() {
        composeRule.onNodeWithText(showMoreButtonText).performClick()
        composeRule.onNodeWithTag(COMMENTS_SECTION).performScrollToNode(
            hasText(loadMoreButtonText)
        )
        composeRule.onNodeWithText(loadMoreButtonText).performClick()
    }
}