package com.example.bizarro.ui.screens.record_details

import androidx.lifecycle.ViewModel
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordDetailsViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository,
) : ViewModel() {

}