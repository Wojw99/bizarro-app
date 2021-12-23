package com.example.bizarro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.ui.theme.kBlueLight
import com.example.bizarro.ui.theme.kWhite

@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(brush = Brush.linearGradient(colors = listOf(kBlack, kBlueDark)), alpha = 0.7f)
            .fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = kWhite)
    }
}