package com.example.bizarro.ui.screens.compare

import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompareViewModel @Inject constructor(
    val appState: AppState
) : ViewModel() {
    init {
        appState.bottomMenuVisible.value = true
    }
}