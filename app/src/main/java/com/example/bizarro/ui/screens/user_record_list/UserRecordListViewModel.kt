package com.example.bizarro.ui.screens.user_record_list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRecordListViewModel @Inject constructor(
    appState: AppState,
    repository: RecordRepository,
) : ViewModel() {
    private val repository: RecordRepository = repository

    val records = mutableStateOf<List<Record>>(listOf())

    init {
        appState.bottomMenuVisible.value = true
    }
}