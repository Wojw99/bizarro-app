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

    //val userProfile = mutableStateOf<List<UserProfile>>(listOf())

    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    var nameUser by mutableStateOf("")
    var emailUser by mutableStateOf("")
    var phoneUser by mutableStateOf("")
    var userDescription by mutableStateOf("")
    var userImage by mutableStateOf(Constants.RECORD_DEFAULT_IMG_URL)

    //val painter = rememberImagePainter(
        //record.imagePaths?.first() ?: Constants.RECORD_DEFAULT_IMG_URL
    //var imageUser by mutableStateOf()

    init {
        appState.bottomMenuVisible.value = true

        GetUserProfile()
    }

   // var string = mutableStateOf("")

//    var nameUser by mutableStateOf("Jan Kowalski")
//    var emailUser by mutableStateOf("jkowalski@gmail.com")
//    var phoneUser by mutableStateOf("123 456 789")
//    var userDescription by mutableStateOf("Użytkownik zajmujący się głównie sprzedażą rowerów sportowych oraz górskich")


    val opinionsLoggedUser= mutableStateOf(listOf(
        "User6: 6, super produkty",
        "User5: 5, dobre produkty",
        "User4: 4, pozytywne produkty",
        "User3: 3, super produkty",
        "User2: 2, super produkty",
        "User1: 1, takie średnie produkty"
    ),

        )

    fun GetUserProfile()
    {
        if (isLoading.value) return
        viewModelScope.launch {

            isLoading.value = true
            val resource = repository.getUserProfile(repository.userId)

            when (resource) {
                is Resource.Success -> {

                    isLoading.value = false
                    val firstNameUser = resource.data?.firstName.toString()
                    val secondNameUser = resource.data?.lastName.toString()
                    //val imageUser = resource.data?.imagePath

                    nameUser = "$firstNameUser $secondNameUser"
                    emailUser = resource.data?.email.toString()
                    phoneUser = resource.data?.phone.toString()
                    userDescription = resource.data?.description.toString()
                    userImage = resource.data?.imagePath.toString()

                    //recordList.value = resource.data ?: listOf()
                    loadError.value = ""
                }
                is Resource.Error<*> -> {
                    isLoading.value = false
                    //val firstNameUser = resource.data?.firstName.toString()
                    //val secondNameUser = resource.data?.lastName.toString()

//                    nameUser = ""
//                    emailUser = ""
//                    phoneUser = ""
//                    userDescription = ""
                    //recordList.value = listOf()
                    loadError.value = resource.message ?: ""
                }
            }

        }

    }




//    fun updateName(name: String)
//    {
//        viewModelScope.launch{
//            nameUser = name
//        }
//
//    }


}