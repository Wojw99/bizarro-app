package com.example.bizarro.ui.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val appState: AppState,
    private val repository: RecordRepository
) : ViewModel() {

    init {
        appState.bottomMenuVisible.value = true
    }

    val text = mutableStateOf("Hello")
}