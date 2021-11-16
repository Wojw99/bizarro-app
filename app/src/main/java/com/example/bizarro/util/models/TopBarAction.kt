package com.example.bizarro.util.models

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarAction(
    val onClick: () -> Unit,
    val icon: ImageVector,
    val contentDescription: String,
)