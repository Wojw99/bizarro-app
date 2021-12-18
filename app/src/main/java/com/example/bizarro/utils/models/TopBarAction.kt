package com.example.bizarro.utils.models

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarAction(
    val onClick: () -> Unit,
    val icon: ImageVector,
    val contentDescription: String,
)