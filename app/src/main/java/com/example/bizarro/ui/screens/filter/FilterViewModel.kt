package com.example.bizarro.ui.screens.filter

import android.widget.SearchView
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.search.SearchViewModel
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.models.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appState: AppState,
) : NetworkingViewModel() {
    val typeLabels = Constants.types
    val categoryLabels = Constants.categories
    val provinceLabels = Constants.provinces

    val selectedType = mutableStateOf("")
    val selectedCategory = mutableStateOf("")
    val selectedProvince = mutableStateOf("")

    val priceMinText = mutableStateOf("")
    val priceMaxText = mutableStateOf("")
    val swapObjectText = mutableStateOf("")
    val rentPeriodText = mutableStateOf("")

    val savingFilterSuccess = mutableStateOf(false)

    init {

    }

    fun cleanStates(){
        val empty = ""

        selectedType.value = empty
        selectedCategory.value = empty
        selectedProvince.value = empty

        priceMinText.value = empty
        priceMaxText.value = empty
        swapObjectText.value = empty
        rentPeriodText.value = empty
    }

    fun saveFilters() {
        val priceMin = if(priceMinText.value.isEmpty()) null else priceMinText.value.toDouble()
        val priceMax = if(priceMaxText.value.isEmpty()) null else priceMaxText.value.toDouble()
        val rentalPeriod = if(rentPeriodText.value.isEmpty()) null else rentPeriodText.value.toInt()

        SearchViewModel.filter = Filter(
            title = SearchViewModel.filter.title,
            type = CommonMethods.convertEmptyStringToNull(selectedType.value),
            category = CommonMethods.convertEmptyStringToNull(selectedCategory.value),
            minPrice = priceMin,
            maxPrice = priceMax,
            province = CommonMethods.convertEmptyStringToNull(selectedProvince.value),
            swapObject = CommonMethods.convertEmptyStringToNull(swapObjectText.value),
            rentalPeriod = rentalPeriod,
        )
        SearchViewModel.signalUpdate()

        savingFilterSuccess.value = true
    }
}