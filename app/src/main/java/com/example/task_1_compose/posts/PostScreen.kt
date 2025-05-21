package com.example.task_1_compose.posts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.R
import com.example.task_1_compose.components.UserImageAndName
import com.example.task_1_compose.components.comments.CommentsSection
import com.example.task_1_compose.components.PostAppBar
import com.example.task_1_compose.data.DisplayItem
import com.example.task_1_compose.data.postsList

@Composable
fun PostScreen(
    index: Int,
    navController: NavController
) {
    val post = postsList[index]
    Scaffold(
        containerColor = Color.White,
        topBar = {
             PostAppBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = dimensionResource(R.dimen.border_stroke_3)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_large_2)
                )
                .fillMaxSize()
                .border(
                    BorderStroke(
                        dimensionResource(R.dimen.border_stroke_3),
                        Color.Black
                    )
                )
                .background(color = Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement
                    .spacedBy(
                        dimensionResource(R.dimen.padding_large)
                    ),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_small),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_small)
                    )
            ) {
                UserImageAndName(post.username)
                Text(
                    text = post.title,
                    modifier = Modifier
                        .padding(
                            dimensionResource(R.dimen.padding_small)
                        ),
                    fontSize = dimensionResource(R.dimen.text_standard).value.sp
                )
                Text(
                    text = post.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_small)
                    ),
                    fontSize = dimensionResource(R.dimen.text_standard).value.sp
                )
                CommentsSection(DisplayItem.PostItem(post))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(index = 4, navController = rememberNavController())
}