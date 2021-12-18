package com.example.bizarro.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.models.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository
) : NetworkingViewModel() {
    val recordList = mutableStateOf<List<Record>>(listOf())
    val nameText = mutableStateOf("")
    val filterList = mutableStateOf(appState.filters)

    init {
        Timber.d("Init")
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
            startLoading()

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
                    endLoading()
                    recordList.value = resource.data ?: listOf()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError()
                    recordList.value = listOf()
                }
            }
        }
    }
}