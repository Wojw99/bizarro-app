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

    var nameUser by mutableStateOf("Jan Kowalski")
    var emailUser by mutableStateOf("jkowalski@gmail.com")
    var phoneUser by mutableStateOf("123 456 789")
    var userDescription by mutableStateOf("Użytkownik zajmujący się głównie sprzedażą rowerów sportowych oraz górskich")


    fun updateName(name: String)
    {
        viewModelScope.launch{
            nameUser = name
        }

    }


}