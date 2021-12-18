package com.example.bizarro.ui.screens.record_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bizarro.ui.Screen

@Composable
fun RecordDetailsScreen(
    navController: NavController,
    viewModel: RecordDetailsViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = viewModel.isLoading.value.toString())
            Text(text = viewModel.recordName.value)
        }
    }
}