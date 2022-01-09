package com.example.bizarro.ui.screens.user_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.repositories.OpinionsRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
    private val opinionsRepository: OpinionsRepository,
) : NetworkingViewModel()
{
    var nameUser by mutableStateOf("")
    var emailUser by mutableStateOf("")
    var phoneUser by mutableStateOf("")
    var userDescription by mutableStateOf("")
    var userImage by mutableStateOf(Constants.RECORD_DEFAULT_IMG_URL)

    val userLoggedOpinionList = mutableStateOf<List<Opinion>>(listOf())

    init {
        appState.bottomMenuVisible.value = true
        getUserProfile()
    }

    fun getUserProfile() {
        if (isLoading.value) return
        viewModelScope.launch {
            startLoading()

            val resource = repository.getUserMe()
            val resource2 = opinionsRepository.getUserWithOpinions(repository.userId!!)

            when (resource) {
                is Resource.Success -> {
                    val firstNameUser = resource.data?.firstName.toString()
                    val secondNameUser = resource.data?.lastName.toString()

                    nameUser = "$firstNameUser $secondNameUser"
                    emailUser = resource.data?.email.toString()
                    phoneUser = resource.data?.phone.toString()
                    userDescription = resource.data?.description.toString()
                    userImage = resource.data?.imagePath.toString()

                    endLoading()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }

            when(resource2){
                is Resource.Success -> {
                    userLoggedOpinionList.value = resource2.data?.opinions ?: listOf()
                    endLoading()
                }
                is Resource.Error -> {
                    userLoggedOpinionList.value = listOf()
                    endLoadingWithError(resource2.message!!)
                }
            }
        }
    }
}