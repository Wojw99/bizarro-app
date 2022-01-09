package com.example.bizarro.ui.screens.record_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.api.models.RecordDetails
import com.example.bizarro.repositories.OpinionsRepository
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.user_record_list.UserRecordListViewModel
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecordDetailsViewModel @Inject constructor(
    val appState: AppState,
    private val userRepository: UserRepository,
    private val opinionsRepository: OpinionsRepository,
    private val recordRepository: RecordRepository,
) : NetworkingViewModel() {
    companion object{
        var record: Record? = null
        var userId: Long? = null

        val signal = MutableLiveData(false)

        fun signalUpdate() {
            signal.value = true
        }
    }

    val topBarTitle = mutableStateOf("")
    val topBarImagePath = mutableStateOf("")

    val recordName = mutableStateOf("")
    val recordHeader = mutableStateOf("")
    val recordLabel = mutableStateOf("")
    val recordCreationDateLabel = mutableStateOf("")
    val recordImagePath = mutableStateOf<String?>(null)

    val recordBody = mutableStateOf("")
    val recordGeneralOpinion = mutableStateOf("")
    val recordGeneralOpinionDesc = mutableStateOf("")
    val recordCategory = mutableStateOf("")
    val recordCategoryDesc = mutableStateOf("")
    val recordAddress = mutableStateOf("")

    val isCurrentUser = mutableStateOf(userId == userRepository.userId)
    val successfullyDeleted = mutableStateOf(false)

    private val observer: Observer<Boolean> = Observer {
        if(it) {
            signal.value = false
            loadNewRecord()
        }
    }

    init{
        updateProfileInfo()
        updateRecordInfo()
        signal.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        signal.removeObserver(observer)
        record = null
        userId = null
    }

    private fun loadNewRecord() {
        viewModelScope.launch {
            startLoading()
            val resource = recordRepository.getRecordDetails(recordId = record!!.id)

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    val recordDetails = resource.data as RecordDetails
                    record = recordDetails.postDetails
                    updateRecordInfo()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    fun deleteRecord() {
        if(record == null) {
            loadError.value = Strings.recordLoadError
            Timber.e("Deleting record failed. Record companion object must be set before initializing this view model.")
            return
        }

        viewModelScope.launch {
            startLoading()
            val resource = recordRepository.deleteRecord(recordId = record!!.id)

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    successfullyDeleted.value = true
                    UserRecordListViewModel.signalUpdate()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    fun updateRecordInfo() {
        if(record == null) {
            loadError.value = Strings.recordLoadError
            Timber.e("Record companion object must be set before initializing this view model.")
            return
        }
        recordName.value = record!!.name
        recordCreationDateLabel.value = "${Strings.added} - ${CommonMethods.convertToLabelDateFormat(record!!.creationDate)}"
        if(record!!.imagePath != null)
            recordImagePath.value = record!!.imagePath.toString()

        if(record!!.type == Constants.TYPE_BUY) {
            recordHeader.value = CommonMethods.convertToPriceFormat(record!!.price)
            recordLabel.value = Strings.titleSectionPurchaseLabel
        } else if (record!!.type == Constants.TYPE_SELL) {
            recordHeader.value = CommonMethods.convertToPriceFormat(record!!.price)
            recordLabel.value = Strings.titleSectionSellLabel
        } else if (record!!.type == Constants.TYPE_SWAP) {
            recordHeader.value = CommonMethods.convertToSwapObjectFormat(record!!.swapObject)
            recordLabel.value = Strings.titleSectionSwapLabel
        } else if (record!!.type == Constants.TYPE_RENT) {
            recordHeader.value = "${CommonMethods.convertToPriceFormat(record!!.price)}, ${CommonMethods.convertToRentalPeriodFormat(
                record!!.rentalPeriod)}"
            recordLabel.value = Strings.titleSectionRentLabel
        }

        recordBody.value = record!!.body
        recordCategory.value = record!!.category
        recordCategoryDesc.value = record!!.category
        recordAddress.value = "${record!!.addressCity}, ${record!!.addressStreet} ${record!!.addressNumber}"
    }

    fun updateProfileInfo(){
        if(userId == null) {
            loadError.value = Strings.recordLoadError
            Timber.e("User id companion object must be set before initializing this view model.")
            return
        }

        viewModelScope.launch {
//            startLoading()
//            val resource = opinionsRepository.getOtherUserProfile(userRepository.userId!!)
//
//            when (resource) {
//                is Resource.Success -> {
//                    endLoading()
//
//                    val profile: UserProfile = resource.data ?: return@launch
//                    topBarTitle.value = "${profile.firstName} ${profile.lastName}"
//                    topBarImagePath.value = profile.imagePath ?: Constants.USER_DEFAULT_IMG_URL
//                    recordGeneralOpinion.value = profile.generalOpinion.name
//                    recordGeneralOpinionDesc.value = profile.generalOpinion.description
//                }
//                is Resource.Error<*> -> {
//                    endLoadingWithError()
//
//                    topBarTitle.value = ""
//                    topBarImagePath.value = ""
//                }
//            }
        }
    }
}