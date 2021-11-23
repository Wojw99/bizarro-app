package com.example.bizarro.ui.screens.user_profile.other_user_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
): ViewModel()
{

    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    var nameUser by mutableStateOf("")
    var emailUser by mutableStateOf("")
    var phoneUser by mutableStateOf("")
    var userDescription by mutableStateOf("")
    var userImage by mutableStateOf(Constants.RECORD_DEFAULT_IMG_URL)

    val userOtherOpinionList = mutableStateOf<List<Opinion>>(listOf())

    //val opinionOtherUserList = mutableStateListOf<String>()

//    val opinionsOtherUser= mutableStateOf(listOf(
//        "User1: 5, super produkty",
//        "User2: 4, dobre produkty",
//        "User3: 3, pozytywne produkty",
//        "User4: 5, super produkty",
//        "User5: 5, super produkty",
//        "User6: 2, takie średnie produkty"
//        ),
//
//        )

//    var nameOtherUser by mutableStateOf("Tomasz Kowalski")
//    var emailOtherUser by mutableStateOf("tkowalski@gmail.com")
//    var phoneOtherUser by mutableStateOf("987 654 321")
//    var userOtherDescription by mutableStateOf("Użytkownik zajmujący się głównie wypożyczaniem rowerów turystycznych")



    init {
        appState.bottomMenuVisible.value = false

        getOtherUserProfile()
    }

//    fun addOpinion(opinionText: String){
//
//         opinionOtherUserList.add(opinionText)
//
//    }

    fun getOtherUserProfile()
    {
        if (isLoading.value) return

        viewModelScope.launch {

            isLoading.value = true

            val otherUserId = 1L

            val resource = repository.getUserProfile(otherUserId)
            val resource2 = repository.getUserOpinions(otherUserId)

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

            when (resource2) {
                is Resource.Success -> {

                    isLoading.value = false

                    userOtherOpinionList.value = resource2.data ?: listOf()

                    loadError.value = ""

                }
                is Resource.Error<*> -> {

                    userOtherOpinionList.value = listOf()

                    isLoading.value = false

                    loadError.value = resource2.message ?: ""
                }
            }

        }
    }

}
