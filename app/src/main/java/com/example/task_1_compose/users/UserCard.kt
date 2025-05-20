package com.example.task_1_compose.users

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task_1_compose.Screen
import com.example.task_1_compose.data.usersList

@Composable
fun UserCard(
    index: Int,
    navController: NavController,
    modifier: Modifier
){
    val user = usersList[index]
    OutlinedCard(
        onClick = {navController.navigate(Screen.UserScreen.name+"/$index")},
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
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp, top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
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
                        text = user.name[0].toString() + (if (user.name.trimEnd()
                                .indexOf(' ') != -1
                        ) user.name[user.name.indexOf(' ') + 1] else ""),
                        fontSize = 20.sp
                    )
                }
                Column {
                    Text(text = user.name)
                    Text(text = user.username)
                }
            }
            Text(
                // TODO: Тут тоже нужно вынести строчки в отдельный файл
                // https://developer.android.com/guide/topics/resources/string-resource#formatting-strings
                text = "Адрес: ${user.address}",
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Номер: ${user.phone}",
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview(){
    UserCard(0, navController = rememberNavController(), modifier = Modifier)
}