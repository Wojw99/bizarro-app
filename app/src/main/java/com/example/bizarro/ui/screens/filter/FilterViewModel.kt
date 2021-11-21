package com.example.bizarro.ui.screens.filter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.Constants
import com.example.bizarro.util.models.CheckState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appState: AppState,
) : ViewModel() {
    val selectedRecordType = mutableStateOf("")
    val selectedCategory = mutableStateOf("")
    val selectedProvince = mutableStateOf("")

    val typeStateList = listOf(
        mutableStateOf(CheckState(Constants.TYPE_BUY, false)),
        mutableStateOf(CheckState(Constants.TYPE_SELL, false)),
        mutableStateOf(CheckState(Constants.TYPE_RENT, false)),
        mutableStateOf(CheckState(Constants.TYPE_SWAP, false)),
    )

    val categoryStateList = listOf(
        mutableStateOf(CheckState(Constants.CATEGORY_BMX, false)),
        mutableStateOf(CheckState(Constants.CATEGORY_GRAVEL, false)),
        mutableStateOf(CheckState(Constants.CATEGORY_MOUNTAIN, false)),
        mutableStateOf(CheckState(Constants.CATEGORY_CRUISER, false)),
        mutableStateOf(CheckState(Constants.CATEGORY_DIRT, false)),
    )

    val provinceStateList = listOf(
        mutableStateOf(CheckState(Constants.provinces[0], false)),
        mutableStateOf(CheckState(Constants.provinces[1], false)),
        mutableStateOf(CheckState(Constants.provinces[2], false)),
        mutableStateOf(CheckState(Constants.provinces[3], false)),
        mutableStateOf(CheckState(Constants.provinces[4], false)),
    )

    init {

    }
}