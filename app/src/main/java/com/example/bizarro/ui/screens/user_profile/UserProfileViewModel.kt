package com.example.bizarro.ui.screens.user_profile

import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val appState: AppState) : ViewModel()
{
    init {
        appState.bottomMenuVisible.value = true
    }
}