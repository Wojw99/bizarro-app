package com.example.bizarro.ui.screens.record_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
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
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository,
) : NetworkingViewModel() {
    val recordId: Long = 1
    val userId: Long = 1

    val topBarTitle = mutableStateOf("")
    val topBarImagePath = mutableStateOf("")

    val recordName = mutableStateOf("")
    val recordHeader = mutableStateOf("")
    val recordLabel = mutableStateOf("")
    val recordCreationDateLabel = mutableStateOf("")
    val recordImagePath = mutableStateOf("")

    val recordBody = mutableStateOf("")
    val recordGeneralOpinion = mutableStateOf("")
    val recordCategory = mutableStateOf("")
    val recordAddress = mutableStateOf("")

    init{
        updateProfileInfo()
        updateRecordInfo()
    }

    private fun updateRecordInfo() {
        viewModelScope.launch {
            startLoading()

            val resource = recordRepository.getRecordDetails(recordId)

            when(resource) {
                is Resource.Success -> {
                    endLoading()

                    val record: Record = resource.data ?: return@launch

                    updateTitleSection(record)
                    recordBody.value = record.body
                    recordGeneralOpinion.value = record.generalOpinion
                    recordCategory.value = record.category
                    recordAddress.value = "${record.address.city}, ${record.address.street}${record.address.number}"
                }
                is Resource.Error -> {
                    endLoadingWithError()
                }
            }
        }
    }

    private fun updateTitleSection(record: Record){
        recordName.value = record.name
        recordCreationDateLabel.value = "Dodano - ${CommonMethods.convertToLabelDateFormat(record.creationDate)}"
        if(record.imagePath != null)
            recordImagePath.value = record.imagePath.toString()

        if(record.type == Constants.TYPE_BUY) {
            recordHeader.value = record.purchasePrice.toString()
            recordLabel.value = Strings.titleSectionPurchaseLabel
        } else if (record.type == Constants.TYPE_SELL) {
            recordHeader.value = record.salePrice.toString()
            recordLabel.value = Strings.titleSectionSellLabel
        } else if (record.type == Constants.TYPE_RENT) {
            recordHeader.value = record.swapObject.toString()
            recordLabel.value = Strings.titleSectionSwapLabel
        } else if (record.type == Constants.TYPE_SWAP) {
            recordHeader.value = "${record.rentalPrice}zł, ${record.rentalPeriod} dni"
            recordLabel.value = Strings.titleSectionRentLabel
        }
    }

    private fun updateProfileInfo(){
        viewModelScope.launch {
            startLoading()

            val resource = userRepository.getUserProfile(userId)

            when (resource) {
                is Resource.Success -> {
                    endLoading()

                    topBarTitle.value = resource.data?.firstName ?: Strings.defaultUserName
                    topBarImagePath.value = resource.data?.imagePath ?: ""
                }
                is Resource.Error<*> -> {
                    endLoadingWithError()

                    topBarTitle.value = ""
                    topBarImagePath.value = ""
                }
            }
        }
    }
}