package com.example.bizarro.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.user_record_list.UserRecordListViewModel
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
    val filter = mutableStateOf(SearchViewModel.filter)

    private val observer: Observer<Boolean> = Observer {
        if(it) {
            signal.value = false
            filter.value = SearchViewModel.filter
            updateRecordList()
        }
    }


    fun getSearchBarInitialText(): String {
        if (filter.value.title == null)
            return ""
        return filter.value.title!!
    }

    init {
        updateRecordList()
        signal.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        signal.removeObserver(observer)
    }

    fun hasFilters(): Boolean {
        if (
            filter.value.type != null
            || filter.value.category != null
            || filter.value.minPrice != null
            || filter.value.maxPrice != null
            || filter.value.province != null
            || filter.value.swapObject != null
            || filter.value.rentalPeriod != null
        ) return true
        return false
    }

    fun updateRecordList() {
        if (isLoading.value) return
        viewModelScope.launch {
            startLoading()

            val resource = repository.getFilteredRecordList(
                title = filter.value.title,
                type = filter.value.type,
                category = filter.value.category,
                minPrice = filter.value.minPrice,
                maxPrice = filter.value.maxPrice,
                province = filter.value.province,
                swapObject = filter.value.swapObject,
                rentalPeriod = filter.value.rentalPeriod,
            )

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    recordList.value = resource.data ?: listOf()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                    recordList.value = listOf()
                }
            }
        }
    }

    companion object{
        var filter = Filter()
        val signal = MutableLiveData(false)

        fun signalUpdate() {
            signal.value = true
        }
    }
}