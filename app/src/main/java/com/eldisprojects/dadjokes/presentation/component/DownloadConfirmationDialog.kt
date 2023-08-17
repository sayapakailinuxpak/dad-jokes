package com.eldisprojects.dadjokes.presentation.component


import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

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