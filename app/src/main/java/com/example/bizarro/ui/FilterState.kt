package com.example.bizarro.ui

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.models.CheckState

class FilterState {
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
}