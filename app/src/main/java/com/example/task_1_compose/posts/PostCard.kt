package com.example.task_1_compose.posts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.Screen
import com.example.task_1_compose.data.postsList

// TODO: код карточки достаточно большой - можно разделить его на компоненты
@Composable
fun PostCard(
    index: Int,
    modifier: Modifier,
    navController: NavController
) {
    var isLiked by remember { mutableStateOf(false) }
    val post = postsList[index]
    OutlinedCard(
        // TODO такие параметры лучше передавать объектом
        // можно сделать класс PostScreenInput и настроить навигацию на объект а не просто url
        onClick = { navController.navigate(Screen.PostScreen.name + "/$index" + "/$isLiked") },
        modifier = modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        shape = CutCornerShape(0.dp),
        border = BorderStroke(3.dp, Color.Black),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White,
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // TODO: такую вьюшку лучше вынести в компоненты чтобы можно было
                // переиспользовать по проекту - можно создать папку components и туда добавлять
                // такие файлы
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(color = Color(0xFFd5d5d5), shape = RoundedCornerShape(50.dp))
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(50.dp))
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = post.username[0].toString() + (if (post.username.trimEnd()
                                .indexOf(' ') != -1
                        ) post.username[post.username.indexOf(' ') + 1] else ""),
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = post.username,
                    fontSize = 25.sp
                )
            }
            Text(
                text = post.title,
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 25.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = post.description,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 25.sp
                )
                Icon(
                    if (isLiked) {
                        Icons.Rounded.Favorite
                    } else {
                        Icons.Rounded.FavoriteBorder
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 2.dp)
                        .size(62.dp)
                        .clickable { isLiked = !isLiked },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCard() {
    PostCard(0, navController = rememberNavController(), modifier = Modifier)
}

