package com.example.bizarro.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ConfirmAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    title: String = "",
    body: String = "",
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(text = "OK", color = colors.onSurface)
                }
            }
        },
        title = { Text(text = title, color = colors.onSurface) },
        text = { Text(text = body, color = colors.onSurface) },
    )
}