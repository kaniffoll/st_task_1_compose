package com.example.task_1_compose.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.task_1_compose.R
import com.example.task_1_compose.components.general.UserImageAndName
import com.example.task_1_compose.data.albumsList
import com.example.task_1_compose.data.dataclasses.Album

@Composable
fun PhotoCard(
    modifier: Modifier,
    index: Int,
    album: Album,
    onClick: () -> Unit
) {
    val photo = album.photos[index]

    OutlinedCustomCard(
        onClick = onClick,
        modifier = modifier
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

@Preview(showBackground = true)
@Composable
fun PhotoPreview() {
    PhotoCard(
        modifier = Modifier,
        album = albumsList[0],
        index = 0
    ) {}
}