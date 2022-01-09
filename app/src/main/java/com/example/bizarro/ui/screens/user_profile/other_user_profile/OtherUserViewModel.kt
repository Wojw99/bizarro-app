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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
    private val opinionsRepository: OpinionsRepository,
) : NetworkingViewModel() {
    companion object {
        var otherUserId = 1L
    }

    var addedOpinionOfUser = ""

    val isSuccess = mutableStateOf(false)

    var nameUser by mutableStateOf("")
    var emailUser by mutableStateOf("")
    var phoneUser by mutableStateOf("")
    var userDescription by mutableStateOf("")
    var userImage by mutableStateOf(Constants.RECORD_DEFAULT_IMG_URL)

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
                    val firstNameUser = resource.data?.userProfile?.firstName.toString()
                    val secondNameUser = resource.data?.userProfile?.lastName.toString()

                    nameUser = "$firstNameUser $secondNameUser"
                    emailUser = resource.data?.userProfile?.email.toString()
                    phoneUser = resource.data?.userProfile?.phone.toString()
                    userDescription = resource.data?.userProfile?.description.toString()
                    userImage = resource.data?.userProfile?.imagePath.toString()

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
