package com.example.bizarro.ui.screens.user_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.api.models.UserProfile
import com.example.bizarro.repositories.OpinionsRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.search.SearchViewModel
import com.example.bizarro.ui.screens.update_user_profile.UpdateUserProfileViewModel
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.Filter
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
    var address by mutableStateOf("")
    var userImage = mutableStateOf<String?>(null)

    val userLoggedOpinionList = mutableStateOf<List<Opinion>>(listOf())

    var userProfile: UserProfile? = null
        private set

    private val observer: Observer<Boolean> = Observer {
        if(it) {
            SearchViewModel.signal.value = false
            getUserProfile()
        }
    }

    init {
        getUserProfile()
        signal.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        signal.removeObserver(observer)
    }

    fun getUserProfile() {
        if (isLoading.value) return
        viewModelScope.launch {
            startLoading()

            val resource = repository.getUserMe()
            val resource2 = opinionsRepository.getUserWithOpinions(repository.userId!!)

            when (resource) {
                is Resource.Success -> {
                    val firstNameUser = resource.data?.firstName
                    val secondNameUser = resource.data?.lastName

                    if(firstNameUser == null && secondNameUser == null) {
                        nameUser = Strings.emptyUserNames
                    } else if(firstNameUser == null && secondNameUser != null) {
                        nameUser = "$$secondNameUser"
                    } else if(firstNameUser != null && secondNameUser == null) {
                        nameUser = "$firstNameUser"
                    } else {
                        nameUser = "$firstNameUser $secondNameUser"
                    }

                    emailUser = resource.data?.email ?: Strings.emptyUserEmail
                    phoneUser = resource.data?.phone ?: Strings.emptyUserPhone
                    userDescription = resource.data?.description ?: Strings.emptyUserDescription
                    address = resource.data?.address ?: Strings.emptyUserAddress
                    userImage.value = resource.data!!.imagePath
                    userProfile = resource.data!!

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

    companion object{
        val signal = MutableLiveData(false)
        fun signalUpdate() {
            signal.value = true
        }
    }
}