package com.example.bizarro.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.utils.Strings

open class NetworkingViewModel : ViewModel() {
    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    fun startLoading() {
        isLoading.value = true
        loadError.value = ""
    }

    fun endLoading() {
        isLoading.value = false
        loadError.value = ""
    }

    fun endLoadingWithError(message: String = Strings.unknownError) {
        isLoading.value = false
        loadError.value = message
    }

    fun clearError(){
        loadError.value = ""
    }

    fun isError(): Boolean {
        return loadError.value != ""
    }
}