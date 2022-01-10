package com.example.bizarro.ui.screens.authenticate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.bizarro.managers.TokenManager
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
class AuthenticateViewModel @Inject constructor(
    val appState: AppState,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
) : NetworkingViewModel() {
    // TODO: remove it
    var userNameLoginText = mutableStateOf("admin")
    val passwordLoginText = mutableStateOf("admin")

    var userNameRegisterText = mutableStateOf("")
    val passwordRegisterText = mutableStateOf("")
    val passwordRepeatRegisterText = mutableStateOf("")
    var emailRegisterText = mutableStateOf("")

    var successfullyLogin = mutableStateOf(false)
    var successfullyRegister = mutableStateOf(false)

    init {
        tryLoadToken()
    }

    private fun tryLoadToken(){
        if(tokenManager.accessTokenExists()) {
            tokenManager.loadAccessToken()
            successfullyLogin.value = true
        }
        val token = tokenManager.accessToken
        val str = ""
    }

    fun login() {
        viewModelScope.launch {
            startLoading()

            val resource = userRepository.login(userNameLoginText.value, passwordLoginText.value)

            when (resource) {
                is Resource.Success -> {
                    tokenManager.saveAccessToken(resource.data!!.accessToken)
                    getUserProfile()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            startLoading()

            val resource = userRepository.getUserMe()

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    userRepository.userId = resource.data!!.userId
                    successfullyLogin.value = true
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    fun register() {
        validateRegisterFields()
        if (isError()) return

        viewModelScope.launch {
            startLoading()

            val resource = userRepository.createUser(
                username = userNameRegisterText.value,
                email = emailRegisterText.value,
                password = passwordRegisterText.value,
                firstName = "",
                lastName = "",
                phone = "",
                addressCity = "",
                addressNumber = "",
                addressProvince = "",
                addressStreet = "",
            )

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    successfullyRegister.value = true
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    private fun validateRegisterFields() {
        if (userNameRegisterText.value.isEmpty()
            || emailRegisterText.value.isEmpty()
            || passwordRegisterText.value.isEmpty()
            || passwordRepeatRegisterText.value.isEmpty()
        ) {
            loadError.value = Strings.emptyFieldsError
        } else if (!CommonMethods.isValidEmail(emailRegisterText.value)) {
            loadError.value = Strings.emailIncorrectError
        } else if (passwordRegisterText.value != passwordRepeatRegisterText.value) {
            loadError.value = Strings.passwordNotEqualsError
        }
    }
}