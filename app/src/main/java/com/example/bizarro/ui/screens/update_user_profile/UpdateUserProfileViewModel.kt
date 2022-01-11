package com.example.bizarro.ui.screens.update_user_profile

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bizarro.api.models.UserProfile
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.search.SearchViewModel
import com.example.bizarro.ui.screens.user_profile.UserProfileViewModel
import com.example.bizarro.ui.screens.user_record_list.UserRecordListViewModel
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class UpdateUserProfileViewModel @Inject constructor(
    val appState: AppState,
    private val userRepository: UserRepository,
) : NetworkingViewModel() {
    var email = mutableStateOf("")
    val firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    val phone = mutableStateOf("")
    val description = mutableStateOf("")
    var addressStreet = mutableStateOf("")

    val url = mutableStateOf<String?>(null)

    val imageUri = mutableStateOf<Uri?>(null)
    val imageBitmap = mutableStateOf<Bitmap?>(null)

    var successfullyUpdate = mutableStateOf(false)

    init {
        if(userProfile != null) {
            email.value = CommonMethods.convertNullToEmptyString(userProfile!!.email)
            firstName.value = CommonMethods.convertNullToEmptyString(userProfile!!.firstName)
            lastName.value = CommonMethods.convertNullToEmptyString(userProfile!!.lastName)
            phone.value = CommonMethods.convertNullToEmptyString(userProfile!!.phone)
            description.value = CommonMethods.convertNullToEmptyString(userProfile!!.description)
            addressStreet.value = CommonMethods.convertNullToEmptyString(userProfile!!.address)
            url.value = userProfile!!.imagePath
        }
    }

    override fun onCleared() {
        super.onCleared()
        userProfile = null
    }

    fun updateProfile(context: Context) {
        viewModelScope.launch {
            startLoading()

            val resource = userRepository.updateUser(
                email.value,
                CommonMethods.convertEmptyStringToNull(firstName.value),
                CommonMethods.convertEmptyStringToNull(lastName.value),
                CommonMethods.convertEmptyStringToNull(phone.value),
                CommonMethods.convertEmptyStringToNull(description.value),
                CommonMethods.convertEmptyStringToNull(addressStreet.value),
            )

            when (resource) {
                is Resource.Success -> {
                    if(imageBitmap.value == null) {
                        endUpdate()
                    } else {
                        updatePhoto(context)
                    }
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    private fun updatePhoto(context: Context) {
        if(userProfile == null) return

        viewModelScope.launch {
            startLoading()

            val file = CommonMethods.convertBitmapToFile(imageBitmap.value!!, context)
            val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)

            val multipartBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.name, requestFile)
                .build()

            val resource = userRepository.addUserPhoto(userProfile!!.userId, multipartBody)

            when (resource) {
                is Resource.Success -> {
                    endUpdate()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    private fun endUpdate() {
        endLoading()
        successfullyUpdate.value = true
        UserProfileViewModel.signalUpdate()
    }

    companion object {
        var userProfile: UserProfile? = null
    }
}