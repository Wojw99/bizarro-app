package com.example.bizarro.ui.screens.user_profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
     appState: AppState) : ViewModel()
{
    init {
        appState.bottomMenuVisible.value = true
    }

    var nameUser = mutableStateOf("Jan Kowalski")
    var emailUser = mutableStateOf("jkowalski@gmail.com")
    var phoneUser = mutableStateOf("123 456 789")

    fun updateName(name: String)
    {
        nameUser.value = name
    }

}