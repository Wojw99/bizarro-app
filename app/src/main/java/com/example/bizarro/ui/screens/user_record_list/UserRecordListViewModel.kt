package com.example.bizarro.ui.screens.user_record_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserRecordListViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
) : NetworkingViewModel() {
    val recordList = mutableStateOf<List<Record>>(listOf())

    init {
        updateRecordList()
    }

    fun updateRecordList(){
        if (isLoading.value) return
        viewModelScope.launch {
            startLoading()

            val resource = repository.getUserRecords(repository.userId)

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