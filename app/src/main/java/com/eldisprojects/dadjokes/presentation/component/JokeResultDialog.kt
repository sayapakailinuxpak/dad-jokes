package com.eldisprojects.dadjokes.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.eldisprojects.dadjokes.R
import com.eldisprojects.dadjokes.data.model.Joke

@OptIn(ExperimentalUnitApi::class)
@Composable
fun JokeResultDialog(
    joke: Joke,
    showDialog: Boolean,
    onDismiss: () -> Unit = {},
    onCopy: () -> Unit = {},
    onDownload: () -> Unit = {}
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    ),
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.funny_face_emoji),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.joke_dialog_title_text),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface,
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = joke.joke,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(horizontal = 32.dp),
                        lineHeight = TextUnit(value = 28.0f, type = TextUnitType.Sp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        ActionButton(
                            iconResource = R.drawable.copy_icon,
                            contentDesc = stringResource(id = R.string.copy_button_content_desc),
                            buttonColor = if (!isSystemInDarkTheme()) colorResource(id = R.color.alice_blue) else colorResource(id = R.color.dodger_blue),
                            iconTintColor = if (isSystemInDarkTheme()) Color.White else null,
                            onClick = onCopy
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        ActionButton(
                            iconResource = R.drawable.image_icon,
                            contentDesc = stringResource(id = R.string.save_as_image_button_content_desc),
                            buttonColor = if (!isSystemInDarkTheme()) colorResource(id = R.color.mimi_pink) else colorResource(
                                id = R.color.razzmatazz
                            ),
                            iconTintColor = if (isSystemInDarkTheme()) Color.White else null,
                            onClick = onDownload
                        )
                    }
                }
            }
        }
    }

}
