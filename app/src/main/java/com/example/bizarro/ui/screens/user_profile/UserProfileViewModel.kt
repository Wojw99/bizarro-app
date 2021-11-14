package com.example.bizarro.ui.screens.user_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
     appState: AppState) : ViewModel()
{
    init {
        appState.bottomMenuVisible.value = true

    }

   // var string = mutableStateOf("")

    var nameUser by mutableStateOf("")
    var emailUser = mutableStateOf("jkowalski@gmail.com")
    var phoneUser = mutableStateOf("123 456 789")



    fun updateName(name: String)
    {
        viewModelScope.launch{
            nameUser = name
        }

    }


}