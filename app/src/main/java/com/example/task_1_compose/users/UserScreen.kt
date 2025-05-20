package com.example.task_1_compose.users

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.BottomNavBar
import com.example.task_1_compose.components.comments.CommentsSection
import com.example.task_1_compose.data.DisplayItem
import com.example.task_1_compose.data.usersList
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.random.Random

// TODO: код экрана достаточно большой - можно разделить его на компоненты
// например хедер, комментраии и тд
@Composable
fun UserScreen(
    index: Int,
    navController: NavController
) {
    val user = usersList[index]
    val randomColor = Color(red = Random.nextInt(0,255), green = Random.nextInt(0,255), blue = Random.nextInt(0,255))
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(modifier = Modifier.height(100.dp))
        },
        bottomBar = {
            BottomNavBar(navController)
            HorizontalDivider(
                color = Color.Black,
                thickness = 3.dp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .border(2.dp, Color.Black)
                        .background(color = randomColor)
                )
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .graphicsLayer {
                            clip = true
                            shape = CircleShape
                            translationY = 75.dp.toPx()
                        }
                        .background(Color(0xFFd5d5d5))
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(100))
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = user.name[0].toString() + (if (user.name.trimEnd()
                                .indexOf(' ') != -1
                        ) user.name[user.name.indexOf(' ') + 1] else ""),
                        fontSize = 60.sp,
                    )
                }
            }

            Spacer(Modifier.height(75.dp))
            Text(
                text = user.name,
                fontSize = 40.sp,
            )
            CommentsSection(DisplayItem.UserItem(user))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserPreview() {
    UserScreen(
        index = 1,
        navController = rememberNavController()
    )
}