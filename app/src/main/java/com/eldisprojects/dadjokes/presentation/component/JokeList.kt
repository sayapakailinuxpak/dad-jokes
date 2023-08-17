package com.eldisprojects.dadjokes.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eldisprojects.dadjokes.data.model.Joke

@Composable
fun JokeList(jokes: List<Joke>, onJokeItemClick: (id: String) -> Unit = {}) {
    LazyColumn(
        content = {
        this.items(jokes) {
            Text(
                text = it.joke,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .clickable {
                        onJokeItemClick(it.id)
                    }
            )
        }
    })
}