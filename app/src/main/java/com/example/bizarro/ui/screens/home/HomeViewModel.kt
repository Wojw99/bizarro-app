package com.example.bizarro.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
) : ViewModel() {

    val labels = listOf("Styczeń", "Grudzień", "Listopad")
    val selectedLabel = mutableStateOf(labels[0])

    init {
        viewModelScope.launch {

        }
    }
}