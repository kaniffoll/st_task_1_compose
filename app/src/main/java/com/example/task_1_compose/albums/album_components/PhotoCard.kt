package com.example.task_1_compose.albums.album_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.task_1_compose.R
import com.example.task_1_compose.components.UserImageAndName
import com.example.task_1_compose.data.Album
import com.example.task_1_compose.data.albumsList

@Composable
fun PhotoCard(
    modifier: Modifier,
    index: Int,
    album: Album,
    onClick: () -> Unit
) {
    val photo = album.photos[index]
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxWidth(),
        shape = RectangleShape,
        border = BorderStroke(
            width = dimensionResource(R.dimen.border_stroke_3),
            color = Color.Black
        ),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White,
        )
    ) {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(
                    dimensionResource(R.dimen.padding_large)
                ),
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small)
                )
        ) {
            UserImageAndName(username = photo.first)
            Image(
                painter = painterResource(photo.second.photo),
                contentDescription = "photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_large)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoPreview() {
    PhotoCard(
        modifier = Modifier,
        album = albumsList[0],
        index = 0
    ) {}
}