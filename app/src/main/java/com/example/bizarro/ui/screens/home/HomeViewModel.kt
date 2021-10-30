package com.example.bizarro.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appState: AppState,
    private val repository: RecordRepository
) : ViewModel() {

    init {
        appState.bottomMenuVisible.value = true
    }
}