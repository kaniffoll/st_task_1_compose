package com.example.task_1_compose.ui.screens.postslist

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.di.ApiModule
import com.example.domain.resources.AppSettings.POSTS_PER_PAGE
import com.example.domain.resources.TestTags.LOADING_INDICATOR
import com.example.domain.resources.TestTags.LOAD_MORE_LIST
import com.example.domain.resources.TestTags.POST_CARD
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
class PostsListViewTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val likeIconContentDescription = context.getString(R.string.like_icon)
    private val filledLikeIconContentDescription = context.getString(R.string.filled_like_icon)
    private val loadingTime = 5000L


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            PostsList(rememberNavController())
        }
    }

    @Test
    fun clickLikeButton() {
        composeRule.waitUntil(loadingTime) {
            composeRule.onAllNodesWithTag(POST_CARD)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithContentDescription(likeIconContentDescription)[0].performClick()
        composeRule.onNodeWithContentDescription(filledLikeIconContentDescription).assertExists()
    }

    @Test
    fun doubleClickLikeButton() {
        composeRule.waitUntil(loadingTime) {
            composeRule.onAllNodesWithTag(POST_CARD)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithContentDescription(likeIconContentDescription)[0].performClick()
        composeRule.onNodeWithContentDescription(filledLikeIconContentDescription).performClick()
        composeRule.onNodeWithContentDescription(filledLikeIconContentDescription)
            .assertDoesNotExist()
    }

    @Test
    fun loadingFirstPost_showLoadingIndicator() {
        composeRule.onNodeWithTag(LOADING_INDICATOR).assertExists()
    }

    @Test
    fun loadingNewPosts_showLoadingIndicator() {
        composeRule.waitUntil(loadingTime) {
            composeRule.onAllNodesWithTag(POST_CARD)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(LOAD_MORE_LIST).performScrollToIndex(POSTS_PER_PAGE - 1)
        composeRule.onNodeWithTag(LOADING_INDICATOR).assertExists()
    }

    @Test
    fun loadingFinished_noLoadingIndicator() {
        composeRule.waitUntil(loadingTime) {
            composeRule.onAllNodesWithTag(POST_CARD)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag(LOADING_INDICATOR).assertDoesNotExist()
    }
}