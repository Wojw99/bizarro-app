package com.example.bizarro.ui.screens.add_record

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRecordViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository,
) : NetworkingViewModel() {

    companion object {
        var record: Record? = null
    }

    val typeLabels = Constants.types
    val categoryLabels = Constants.categories
    val provinceLabels = Constants.provinces

    val selectedType = mutableStateOf("")
    val selectedCategory = mutableStateOf("")
    val selectedProvince = mutableStateOf("")

    val priceText = mutableStateOf("")
    val swapObjectText = mutableStateOf("")
    val rentPeriodText = mutableStateOf("")

    val titleText = mutableStateOf("")
    val descriptionText = mutableStateOf("")
    val cityText = mutableStateOf("")
    val streetText = mutableStateOf("")
    val numberText = mutableStateOf("")

    init {
        if(record != null) {
            updateWithRecord(record!!)
        }
    }

    private fun updateWithRecord(record: Record) {
        selectedType.value = record.type
        selectedCategory.value = record.category.name
        selectedProvince.value = record.address.province

        // TODO: Change it to type specific price handling
        priceText.value = record.purchasePrice.toString()
        swapObjectText.value = record.swapObject.toString()
        rentPeriodText.value = record.rentalPeriod.toString()

        titleText.value = record.name
        descriptionText.value = record.body
        cityText.value = record.address.city
        streetText.value = record.address.street
        numberText.value = record.address.number
    }

    fun cleanStates(){
        val empty = ""

        selectedType.value = empty
        selectedCategory.value = empty
        selectedProvince.value = empty

        // TODO: Change it to type specific price handling
        priceText.value = empty
        swapObjectText.value = empty
        rentPeriodText.value = empty

        titleText.value = empty
        descriptionText.value = empty
        cityText.value = empty
        streetText.value = empty
        numberText.value = empty
    }
}