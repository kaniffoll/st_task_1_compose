package com.example.task_1_compose.posts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.data.postsList

@Composable
fun PostScreen(
    index: Int,
    isLikeClicked: Boolean,
    navController: NavController
){
    val post = postsList[index]
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(
                modifier = Modifier.height(100.dp).fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
                Icon(
                    if(isLikeClicked){
                        Icons.Rounded.Favorite
                    }
                    else{
                        Icons.Rounded.FavoriteBorder
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 2.dp)
                        .size(62.dp)
                )
            }
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = 3.dp
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 15.dp, end = 15.dp, bottom = 30.dp)
                .fillMaxSize()
                .border(
                    BorderStroke(3.dp, Color.Black)
                )
                .background(color = Color.White)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .background(color = Color(0xFFd5d5d5), shape = RoundedCornerShape(50.dp))
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(50.dp))
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = post.name[0].toString() + (if (post.name.trimEnd()
                                    .indexOf(' ') != -1
                            ) post.name[post.name.indexOf(' ') + 1] else ""),
                            fontSize = 20.sp
                        )
                    }
                    Text(
                        text = post.name,
                        fontSize = 25.sp
                    )
                }
                Text(
                    text = post.title,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 25.sp
                )
                Text(
                    text = post.description,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                    fontSize = 25.sp
                )
                var count by remember { mutableIntStateOf(post.comments.size) }
                var canLoadMore by remember { mutableStateOf(2 < post.comments.size) }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item{
                        Text(
                            text = "Комментарии:",
                            modifier = Modifier.padding(start = 10.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (canLoadMore){
                        count = 2
                    }
                    items(count){ index ->
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .border(BorderStroke(2.dp, color = Color.Black))
                            ,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Text(
                                text = post.comments[index].first,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 10.dp, top = 15.dp)
                            )
                            Text(
                                text = post.comments[index].second,
                                fontSize = 25.sp,
                                modifier = Modifier.padding(start = 10.dp, bottom = 15.dp)
                            )
                        }
                    }
                    item {
                        if(canLoadMore){
                            Text(
                                text = "Показать больше",
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        canLoadMore = !canLoadMore
                                        count = post.comments.size
                                    },
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview(){
    PostScreen(index = 4, isLikeClicked = false, navController = rememberNavController())
}