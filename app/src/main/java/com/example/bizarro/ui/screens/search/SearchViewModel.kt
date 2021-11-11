package com.example.bizarro.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository
) : ViewModel() {
    val recordList = mutableStateOf<List<Record>>(listOf())
    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    val nameText = mutableStateOf("")

    val cityFilter = mutableStateOf<String?>(null)
    val provinceFilter = mutableStateOf<String?>(null)
    val typeFilter = mutableStateOf<String?>(null)

    init {
        updateRecordList()
    }

    fun updateRecordList() {
        if (isLoading.value) return
        viewModelScope.launch {
            isLoading.value = true
            delay(2000L)
            val resource = repository.getRecordList(
                0,
                0,
                if (nameText.value == "") null else nameText.value,
                cityFilter.value,
                provinceFilter.value,
                typeFilter.value
            )

            when (resource) {
                is Resource.Success -> {
                    isLoading.value = false
                    recordList.value = resource.data ?: listOf()
                    loadError.value = ""
                }
                is Resource.Error<*> -> {
                    isLoading.value = false
                    recordList.value = listOf()
                    loadError.value = resource.message ?: ""
                }
            }
        }
    }
}