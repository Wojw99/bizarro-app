package com.example.bizarro.ui.screens.filter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.Screen
import com.example.bizarro.util.Constants
import com.example.bizarro.util.models.CheckState
import com.example.bizarro.util.models.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appState: AppState,
) : ViewModel() {
    val typeStateList = mapOf(
        Constants.TYPE_BUY to mutableStateOf(false),
        Constants.TYPE_SELL to mutableStateOf(false),
        Constants.TYPE_RENT to mutableStateOf(false),
        Constants.TYPE_SWAP to mutableStateOf(false),
    )

    val categoryStateList = mapOf(
        Constants.CATEGORY_BMX to mutableStateOf(false),
        Constants.CATEGORY_GRAVEL to mutableStateOf(false),
        Constants.CATEGORY_MOUNTAIN to mutableStateOf(false),
        Constants.CATEGORY_CRUISER to mutableStateOf(false),
        Constants.CATEGORY_DIRT to mutableStateOf(false),
    )

    val provinceStateList = mapOf(
        Constants.provinces[0] to mutableStateOf(false),
        Constants.provinces[1] to mutableStateOf(false),
        Constants.provinces[2] to mutableStateOf(false),
        Constants.provinces[3] to mutableStateOf(false),
        Constants.provinces[4] to mutableStateOf(false),
    )

    init {

    }

    fun cleanStates(){
        for (type in typeStateList)
            type.value.value = false

        for (category in categoryStateList)
            category.value.value = false

        for (province in provinceStateList)
            province.value.value = false
    }

    fun loadFilters() {
        val provinceFilter = appState.filters.first { f -> f.name == Constants.FILTER_PROVINCE }

    }

    fun saveFilters() {
        val filters = mutableListOf<Filter>()

        for (state in typeStateList) {
            if (state.value.value) filters.add(
                Filter(
                    Constants.FILTER_TYPE,
                    listOf(state.key),
                )
            )
        }

        for (state in provinceStateList) {
            if (state.value.value) filters.add(
                Filter(
                    Constants.FILTER_PROVINCE,
                    listOf(state.key),
                )
            )
        }

        for (state in categoryStateList) {
            if (state.value.value) filters.add(
                Filter(
                    Constants.FILTER_CATEGORY,
                    listOf(state.key),
                )
            )
        }

        appState.filters = filters
    }
}