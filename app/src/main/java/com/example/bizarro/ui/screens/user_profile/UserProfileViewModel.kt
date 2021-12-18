package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.data.remote.responses.UserProfile
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    appState: AppState,
    private val repository: UserRepository,) : ViewModel()
{

    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

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


    fun getUserProfile()
    {
        if (isLoading.value) return
        viewModelScope.launch {

            isLoading.value = true

            val resource = repository.getUserProfile(repository.userId)
            val resource2 = repository.getUserOpinions(repository.userId)

            when (resource) {
                is Resource.Success -> {

                    isLoading.value = false

                    val firstNameUser = resource.data?.firstName.toString()
                    val secondNameUser = resource.data?.lastName.toString()

                    nameUser = "$firstNameUser $secondNameUser"
                    emailUser = resource.data?.email.toString()
                    phoneUser = resource.data?.phone.toString()
                    userDescription = resource.data?.description.toString()
                    userImage = resource.data?.imagePath.toString()

                    loadError.value = ""

                }
                is Resource.Error<*> -> {

                    isLoading.value = false

                    loadError.value = resource.message ?: ""
                }
            }

            when(resource2){
                is Resource.Success -> {
                    isLoading.value = false

                    userLoggedOpinionList.value = resource2.data ?: listOf()
                }
                is Resource.Error -> {

                    isLoading.value = false

                    userLoggedOpinionList.value = listOf()

                    loadError.value = resource2.message ?: ""
                }
            }

        }

    }
}