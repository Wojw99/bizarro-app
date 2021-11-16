package com.example.bizarro.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Resource
import com.example.bizarro.util.models.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    val nameText = mutableStateOf("")

    val filterList = mutableStateOf(listOf(
        Filter(Constants.FILTER_CITY, listOf()),
        Filter(Constants.FILTER_PROVINCE, listOf()),
        Filter(Constants.FILTER_TYPE, listOf()),
        Filter(Constants.FILTER_CATEGORY, listOf()),
    ))

    init {
        updateRecordList()
    }

    private fun getFilterValue(filter: String) : String? {
        for(x in filterList.value) {
            if(filter == x.name && x.values.isNotEmpty())
                return x.values.first()
        }
        return null
    }

    fun hasFilters() : Boolean{
        for(x in filterList.value) {
            if(x.values.isNotEmpty())
                return true
        }
        return false
    }

    fun clearFilterAtIndex(index: Int) {
        filterList.value[index].values = listOf()
    }

    fun addTestFilers() {
        filterList.value = listOf(
            Filter(Constants.FILTER_CITY, listOf()),
            Filter(Constants.FILTER_PROVINCE, listOf("śląskie")),
            Filter(Constants.FILTER_TYPE, listOf("sprzedam")),
            Filter(Constants.FILTER_CATEGORY, listOf("górski")),
        )
    }

    fun updateRecordList() {
        if (isLoading.value) return
        viewModelScope.launch {
            isLoading.value = true
            Timber.d("Search for ${nameText.value}")
            val resource = repository.getRecordList(
                0,
                0,
                if (nameText.value == "") null else nameText.value,
                getFilterValue(Constants.FILTER_CITY),
                getFilterValue(Constants.FILTER_PROVINCE),
                getFilterValue(Constants.FILTER_TYPE),
                getFilterValue(Constants.FILTER_CATEGORY),
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