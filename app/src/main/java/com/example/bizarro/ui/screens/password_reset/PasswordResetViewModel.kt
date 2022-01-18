package com.example.bizarro.ui.screens.password_reset

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.ValidationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordResetViewModel @Inject constructor(
    val appState: AppState,
    private val userRepository: UserRepository,
) : NetworkingViewModel() {
    val email = mutableStateOf("")
    val code = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordRepeat = mutableStateOf("")

    val isResetRequestSent = mutableStateOf(false)
    val isSuccess = mutableStateOf(false)

    fun sendResetRequest() {
        loadError.value = ValidationHelper.validateEmail(email.value)
        if(isError()) return

        viewModelScope.launch {
            startLoading()

            val resource = userRepository.sendResetPasswordRequest(email.value)
            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    isResetRequestSent.value = true
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    fun resetPassword() {
        loadError.value = ValidationHelper.validatePasswordAndCode(
            code.value,
            password.value,
            passwordRepeat.value,
        )
        if(isError()) return

        viewModelScope.launch {
            startLoading()

            val resource = userRepository.resetPassword(
                token = code.value,
                password = password.value,
                passwordRepeat = passwordRepeat.value,
            )

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    isSuccess.value = true
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }
}