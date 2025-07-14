package com.example.task_1_compose.ui.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.task_1_compose.R
import com.example.domain.data.Album

@Composable
fun AlbumCard(
    album: Album,
    onClick: () -> Unit
) {
    OutlinedCustomCard(onClick = onClick, modifier = Modifier) {
        Text(
            text = album.title,
            fontSize = dimensionResource(R.dimen.text_small).value.sp,
            modifier = Modifier.padding(
                dimensionResource(R.dimen.padding_medium)
            )
        )
    }
}