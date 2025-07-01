package com.example.task_1_compose.ui.screens.imagepager

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pinch
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.di.ApiModule
import com.example.domain.resources.TestTags.IMAGE_PAGER
import com.example.domain.resources.mocks.mockAlbum
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
class ImagePagerTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val photo = context.getString(R.string.photo)

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
                    targetState = mockAlbum, label = ""
                ) {
                    ImagePager(
                        album = it,
                        initialImage = 0,
                        navController = rememberNavController(),
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@AnimatedContent
                    )
                }
            }
        }
    }

    @Test
    fun swipeToRightImage() {
        composeRule.onNodeWithContentDescription("$photo 0").assertExists()
        composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
            swipeLeft()
        }
        composeRule.onNodeWithContentDescription("$photo 1").assertExists()
    }

    @Test
    fun cannotSwipeBeforeFirstImage() {
        composeRule.onNodeWithContentDescription("$photo 0").assertExists()
        composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
            swipeRight()
        }
        composeRule.onNodeWithContentDescription("$photo 0").assertExists()
    }

    @Test
    fun swipeToRightThanSwipeToLeftImage() {
        composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
            swipeLeft()
        }
        composeRule.onNodeWithContentDescription("$photo 1").assertExists()
        composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
            swipeRight()
        }
        composeRule.onNodeWithContentDescription("$photo 0").assertExists()
    }

    @Test
    fun cannotSwipeAfterLastImage() {
        for (i in mockAlbum.photos.indices) {
            composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
                swipeLeft()
            }
        }
        composeRule.onNodeWithContentDescription("$photo ${mockAlbum.photos.size-1}").assertExists()
        composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
            swipeLeft()
        }
        composeRule.onNodeWithContentDescription("$photo ${mockAlbum.photos.size-1}").assertExists()
    }

    @Test
    fun pinchImage() {
        composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
            pinch(
                start0 = centerLeft,
                start1 = centerRight,
                end0 = topLeft,
                end1 = bottomRight
            )
        }
        composeRule.onNodeWithContentDescription("$photo 0").assertExists()
    }

    @Test
    fun pagerDisplaysAllImages() {
        for (i in mockAlbum.photos.indices) {
            composeRule.onNodeWithContentDescription("$photo $i").assertExists()
            composeRule.onNodeWithTag(IMAGE_PAGER).performTouchInput {
                swipeLeft()
            }
        }
        composeRule.waitForIdle()
    }
}