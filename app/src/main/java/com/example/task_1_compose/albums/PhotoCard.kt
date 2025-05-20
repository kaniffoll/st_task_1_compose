package com.example.task_1_compose.albums

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.data.albumsList

// TODO: код карточки достаточно большой - можно разделить его на компоненты
// например - вынести отдельно Column как PhotoCardContent - верхнюю часть
// с аватаркой и тд вынести как PhotoCardTopContent - так будет читаемее
@Composable
fun PhotoCard(
    modifier: Modifier,
    index: Int,
    albumIndex: Int,
    // TODO: клик на карточку лучше вынести в место где карточка используется
    // а не передавать параметром, там как раз будет доступен и индекс
    // чтобы обработать нажатие, плюс лучше сделать всю карточку кликабельной
    onClick: (Int) -> Unit
) {
    // TODO: тут пропущена пустая строчка = тоже мелочь, но все таки
    val photo = albumsList[albumIndex].photos[index]
    OutlinedCard(
        onClick = {},
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
                        text = photo.first[0].toString() + (if (photo.first.trimEnd()
                                .indexOf(' ') != -1
                        ) photo.first[photo.first.indexOf(' ') + 1] else ""),
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = photo.first,
                    fontSize = 25.sp
                )
            }
            Image(
                painter = painterResource(photo.second.photo),
                contentDescription = "photo",
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
                    .clickable {onClick(index)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoPreview() {
    PhotoCard(
        modifier = Modifier,
        albumIndex = 0,
        index = 0,
        onClick = {}
    )
}