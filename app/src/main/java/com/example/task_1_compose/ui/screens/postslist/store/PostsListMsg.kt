package com.example.task_1_compose.ui.screens.postslist.store

import com.example.domain.data.Post
import com.example.domain.statefuldata.StatefulData

sealed interface PostsListMsg {
    class LikeClicked(val posts: List<Post>) : PostsListMsg
    data object LikeFailed : PostsListMsg
    data object AllPostsLoaded : PostsListMsg
    class NextPostsLoaded(val posts: List<Post>) : PostsListMsg
    class PostsLoadError(val statefulData: StatefulData<List<Post>>) : PostsListMsg
}