package com.example.bizarro.ui.screens.user_profile.other_user_profile

import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
    appState: AppState
): ViewModel()
{
    init {
        appState.bottomMenuVisible.value = false
    }


}