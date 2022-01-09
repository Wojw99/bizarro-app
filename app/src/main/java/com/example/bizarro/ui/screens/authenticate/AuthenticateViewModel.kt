package com.example.bizarro.ui.screens.authenticate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    val appState: AppState,
    private val userRepository: UserRepository,
): NetworkingViewModel() {
    // TODO: remove it
    var emailLoginText = mutableStateOf("admin")
    val passwordLoginText = mutableStateOf("admin")

    var emailRegisterText = mutableStateOf("")
    val passwordRegisterText = mutableStateOf("")

    var successfullyLogin = mutableStateOf(false)

    fun login() {
        viewModelScope.launch {
            startLoading()

            val resource = userRepository.login(emailLoginText.value, passwordLoginText.value)

            when (resource) {
                is Resource.Success -> {
                    getUserProfile()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    fun getUserProfile() {
        viewModelScope.launch {
            startLoading()

            val resource = userRepository.getUserMe()

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    successfullyLogin.value = true
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            startLoading()
            delay(1000L)
            successfullyLogin.value = true
        }
    }
}