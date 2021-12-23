package com.example.bizarro.ui.screens.compare

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CompareScreen(
    navController: NavController,
    viewModel: CompareViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = true

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // * * * * * * * TOP BAR * * * * * * *

        // * * * * * * * COMPARE LIST * * * * * * *
    }
}