package com.example.bizarro.ui.screens.compare

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CompareViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository,
) : NetworkingViewModel() {
    val recordList = mutableStateOf<List<Record>>(listOf())

    init {
        updateRecordList()
    }

    fun getHeader(record: Record): String {
        if(record.type == Constants.TYPE_BUY) {
            return "${record.price}${Strings.priceSuffix}"
        } else if (record.type == Constants.TYPE_SELL) {
            return "${record.price}${Strings.priceSuffix}"
        } else if (record.type == Constants.TYPE_SWAP) {
            return "${record.swapObject}"
        } else if (record.type == Constants.TYPE_RENT) {
            return "${record.price}${Strings.priceSuffix}, ${record.rentalPeriod} ${Strings.days}"
        }
        throw IllegalArgumentException("Unrecognized type!")
    }

    fun getLabel(record: Record): String{
        if(record.type == Constants.TYPE_BUY) {
            return Strings.titleSectionPurchaseLabel
        } else if (record.type == Constants.TYPE_SELL) {
            return Strings.titleSectionSellLabel
        } else if (record.type == Constants.TYPE_SWAP) {
            return Strings.titleSectionSwapLabel
        } else if (record.type == Constants.TYPE_RENT) {
            return Strings.titleSectionRentLabel
        }
        throw IllegalArgumentException("Unrecognized type!")
    }

    fun updateRecordList() {

    }
}