package com.example.bizarro.ui.screens.user_profile.other_user_profile

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
import com.example.bizarro.ui.screens.search.SearchViewModel
import com.example.bizarro.ui.screens.user_record_list.UserRecordListViewModel
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
    val appState: AppState,
    private val opinionsRepository: OpinionsRepository,
) : NetworkingViewModel() {
    companion object {
        var otherUserId = 1L
    }

    var addedOpinionOfUser = ""

    val isSuccess = mutableStateOf(false)

    var nameUser by mutableStateOf("")
    var userName by mutableStateOf("")
    var emailUser by mutableStateOf("")
    var phoneUser by mutableStateOf("")
    var userDescription by mutableStateOf("")
    var address by mutableStateOf("")
    var userImage = mutableStateOf<String?>(Constants.RECORD_DEFAULT_IMG_URL)

    val textOpinion = mutableStateOf("")
    val selectedReview = mutableStateOf(Review.review3)

    val userOtherOpinionList = mutableStateOf<List<Opinion>>(listOf())

    init {
        appState.bottomMenuVisible.value = false
        getOtherUserProfile()
    }

    override fun onCleared() {
        super.onCleared()
        otherUserId = 1L
    }

    fun addOpinion() {
        viewModelScope.launch {
            startLoading()

            val resource = opinionsRepository.createOpinion(
                otherUserId,
                textOpinion.value,
                selectedReview.value.toInt(),
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

    fun getOtherUserProfile() {
        if (isLoading.value) return

        viewModelScope.launch {
            startLoading()

            val resource = opinionsRepository.getOtherUserProfile(otherUserId)
            val resource2 = opinionsRepository.getUserWithOpinions(otherUserId)

            when (resource) {
                is Resource.Success -> {
                    val profile = resource.data!!.userProfile
                    val firstNameUser = profile.firstName
                    val secondNameUser = profile.lastName

                    if(firstNameUser.isNullOrEmpty() && secondNameUser.isNullOrEmpty()) {
                        nameUser = Strings.emptyUserNames
                    } else if(firstNameUser.isNullOrEmpty() && !secondNameUser.isNullOrEmpty()) {
                        nameUser = "$secondNameUser"
                    } else if(!firstNameUser.isNullOrEmpty() && secondNameUser.isNullOrEmpty()) {
                        nameUser = "$firstNameUser"
                    } else {
                        nameUser = "$firstNameUser $secondNameUser"
                    }

                    userName = profile.username
                    emailUser = if (profile.email.isNullOrEmpty()) Strings.emptyUserEmail else profile.email
                    phoneUser = if (profile.phone.isNullOrEmpty()) Strings.emptyUserPhone else profile.phone
                    userDescription = if (profile.description.isNullOrEmpty()) Strings.emptyUserDescription else profile.description
                    address = if (profile.address.isNullOrEmpty()) Strings.emptyUserAddress else profile.address
                    userImage.value = profile.imagePath

                    endLoading()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }

            when (resource2) {
                is Resource.Success -> {
                    userOtherOpinionList.value = resource2.data?.opinions ?: listOf()
                    endLoading()
                }
                is Resource.Error<*> -> {
                    userOtherOpinionList.value = listOf()
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }
}
