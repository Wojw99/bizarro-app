package com.example.bizarro.ui.screens.filter

import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appState: AppState,
) : ViewModel() {
    init {

    }
}