package com.example.bizarro.ui.screens.user_profile.other_user_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
): ViewModel()
{
    companion object{
        var otherUserId = 1L
    }

    var addedOpinionOfUser = ""

    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    var nameUser by mutableStateOf("")
    var emailUser by mutableStateOf("")
    var phoneUser by mutableStateOf("")
    var userDescription by mutableStateOf("")
    var userImage by mutableStateOf(Constants.RECORD_DEFAULT_IMG_URL)

    val userOtherOpinionList = mutableStateOf<List<Opinion>>(listOf())

    init {
        appState.bottomMenuVisible.value = false

        getOtherUserProfile()
    }

    override fun onCleared() {
        super.onCleared()
        otherUserId = 1L
    }

    fun addOpinion(opinionContent: String, opinionRating: Int){

        if(isLoading.value) return

        viewModelScope.launch {

            isLoading.value = true

            when (repository.addUserOpinion(
                Opinion(-1, otherUserId,
                LocalDate.now()
                //CommonMethods.convertToRecordBoxDateFormat(localDate)
                ,
                opinionRating, opinionContent)
            )){

                is Resource.Success -> {

                    isLoading.value = false
                    loadError.value = ""
                }
                is Resource.Error<*> -> {

                    isLoading.value = false

                    loadError.value = repository.addUserOpinion(Opinion(-1, otherUserId, LocalDate.now(), opinionRating, opinionContent)).message ?: ""
                }
            }

        }

    }

    fun getOtherUserProfile()
    {
        if (isLoading.value) return

        viewModelScope.launch {

            isLoading.value = true

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
