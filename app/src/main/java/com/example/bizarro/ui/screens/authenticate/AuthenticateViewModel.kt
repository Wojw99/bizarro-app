package com.example.bizarro.ui.screens.authenticate

import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    appState: AppState
): ViewModel() {

    init{
        appState.bottomMenuVisible.value = false
    }

}