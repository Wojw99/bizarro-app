package com.example.bizarro.ui

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.models.Filter
import javax.inject.Singleton

@Singleton
class AppState {
    val bottomMenuVisible = mutableStateOf(true)

    var filters = listOf(
        Filter(Constants.FILTER_CITY, listOf()),
        Filter(Constants.FILTER_PROVINCE, listOf()),
        Filter(Constants.FILTER_TYPE, listOf()),
        Filter(Constants.FILTER_CATEGORY, listOf()),
    )

    fun showBottomMenu() {
        if(!bottomMenuVisible.value) bottomMenuVisible.value = true
    }

    fun hideBottomMenu() {
        if(bottomMenuVisible.value) bottomMenuVisible.value = false
    }
}