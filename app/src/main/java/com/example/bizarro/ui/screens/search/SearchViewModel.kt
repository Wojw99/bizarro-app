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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository
) : ViewModel() {
    val recordList = mutableStateOf<List<Record>>(listOf())
    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    val name = mutableStateOf<String?>(null)
    val city = mutableStateOf<String?>(null)
    val province = mutableStateOf<String?>(null)
    val type = mutableStateOf<String?>(null)

    init {
        updateRecordList()
    }

    private fun updateRecordList() {
        viewModelScope.launch {
            isLoading.value = true
            delay(2000L)
            val resource = repository.getRecordList(
                0,
                0,
                name.value,
                city.value,
                province.value,
                type.value
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