package com.eldisprojects.dadjokes.presentation.components


import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties

@Composable
fun DownloadConfirmationDialog(
    title: String,
    desc: String,
    openDialog: Boolean,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = title)},
            text = { Text(text = desc) },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(text = "Confirm", color = MaterialTheme.colors.onSurface)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Dismiss", color = MaterialTheme.colors.onSurface)
                }
            },
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        )
    }
}