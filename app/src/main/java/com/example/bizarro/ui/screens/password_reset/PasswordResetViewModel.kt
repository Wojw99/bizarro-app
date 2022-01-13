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
        loadError.value = validateEmail()
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
        loadError.value = validatePasswordAndCode()
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

    private fun validateEmail(): String {
        if (email.value.isEmpty() ) {
            return Strings.emptyFieldsError
        } else if (!CommonMethods.isValidEmail(email.value)) {
            return Strings.emailIncorrectError
        }
        return Strings.empty
    }

    private fun validatePasswordAndCode(): String {
        if (code.value.isEmpty()
            || password.value.isEmpty()
            || passwordRepeat.value.isEmpty()
        ) {
            return Strings.emptyFieldsError
        } else if (password.value != passwordRepeat.value) {
            return Strings.passwordNotEqualsError
        }
        return Strings.empty
    }
}