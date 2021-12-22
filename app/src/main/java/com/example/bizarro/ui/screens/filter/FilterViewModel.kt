package com.example.bizarro.ui.screens.filter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.models.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appState: AppState,
) : ViewModel() {
    val typeLabels = Constants.types
    val categoryLabels = Constants.categories
    val provinceLabels = Constants.provinces

    val selectedType = mutableStateOf("")
    val selectedCategory = mutableStateOf("")
    val selectedProvince = mutableStateOf("")

    // TODO: Change it to type specific price handling
    val priceMinText = mutableStateOf("")
    val priceMaxText = mutableStateOf("")
    val swapObjectText = mutableStateOf("")
    val rentPeriodText = mutableStateOf("")

    init {

    }

    fun cleanStates(){
        val empty = ""

        selectedType.value = empty
        selectedCategory.value = empty
        selectedProvince.value = empty

        // TODO: Change it to type specific price handling
        priceMinText.value = empty
        priceMaxText.value = empty
        swapObjectText.value = empty
        rentPeriodText.value = empty
    }

    fun saveFilters() {

    }
}