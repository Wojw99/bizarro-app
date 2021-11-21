package com.example.bizarro.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.repositories.RecordRepository
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
    init {
        viewModelScope.launch {
            val profile = repository.getUserProfile(0)
            Timber.d(profile.data.toString())

            val opinions = repository.getUserOpinions(0)
            Timber.d(opinions.data.toString())
        }
    }
}