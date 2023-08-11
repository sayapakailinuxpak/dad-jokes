package com.eldisprojects.dadjokes.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eldisprojects.dadjokes.data.model.Joke

@Composable
fun JokeList(jokes: List<Joke>) {
    LazyColumn(
//        modifier = Modifier.border(width = 1.dp, color = Color.Blue),
        content = {
        this.items(jokes) {
            Text(
                text = it.joke,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Green)
                    .clickable {

                    }
            )
        }
    })
}