package com.example.bizarro.ui

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Strings
import com.example.bizarro.util.models.Filter

class AppState {
    val bottomMenuVisible = mutableStateOf(true)

    var filters = listOf(
        Filter(Constants.FILTER_CITY, listOf()),
        Filter(Constants.FILTER_PROVINCE, listOf()),
        Filter(Constants.FILTER_TYPE, listOf()),
        Filter(Constants.FILTER_CATEGORY, listOf()),
    )
}