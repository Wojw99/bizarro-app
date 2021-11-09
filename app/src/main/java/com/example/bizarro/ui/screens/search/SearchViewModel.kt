package com.example.bizarro.ui.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val appState: AppState,
    private val repository: RecordRepository
) : ViewModel() {
    val records = mutableStateOf<List<Record>>(listOf())
    val singleRecord = mutableStateOf<Record?>(null)
    val singleRecord2 = mutableStateOf<Record?>(null)

    init {
        appState.bottomMenuVisible.value = true
        updateList()
        updateSingleRecords()
    }

    private fun updateList() {
        viewModelScope.launch {
            records.value = repository.getRecordList(0,0).data ?: listOf()
        }
    }

    private fun updateSingleRecords() {
        viewModelScope.launch {
            val resource = repository.getRecordDetails(0)
            val resource2 = repository.getRecordDetails(1)
            singleRecord.value = resource.data
            singleRecord2.value = resource2.data
        }
    }
}