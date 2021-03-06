package com.example.bizarro.ui.screens.add_record

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.screens.search.SearchViewModel
import com.example.bizarro.ui.screens.user_record_list.UserRecordListViewModel
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import javax.inject.Inject
import okhttp3.RequestBody
import timber.log.Timber


@HiltViewModel
class AddRecordViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository,
) : NetworkingViewModel() {

    companion object {
        var record: Record? = null
    }

    val typeLabels = Constants.types
    val categoryLabels = Constants.categories.map { category -> category.name }
    val provinceLabels = Constants.provinces

    val selectedType = mutableStateOf("")
    val selectedCategory = mutableStateOf("")
    val selectedProvince = mutableStateOf("")

    val priceText = mutableStateOf("")
    val swapObjectText = mutableStateOf("")
    val rentPeriodText = mutableStateOf("")

    val titleText = mutableStateOf("")
    val descriptionText = mutableStateOf("")
    val cityText = mutableStateOf("")
    val streetText = mutableStateOf("")
    val numberText = mutableStateOf("")

    val isSuccess = mutableStateOf(false)
    val isEditScreen = mutableStateOf(record != null)

    val imageUri = mutableStateOf<Uri?>(null)
    val imageBitmap = mutableStateOf<Bitmap?>(null)

    init {
        if (record != null) {
            updateViewWithRecord(record!!)
        }
    }

    override fun onCleared() {
        super.onCleared()
        record = null
    }

    private fun updateViewWithRecord(record: Record) {
        selectedType.value = record.type
        selectedCategory.value = record.category
        selectedProvince.value = record.addressProvince

        priceText.value = if(record.price == null) "" else record.price.toString()
        swapObjectText.value = if(record.swapObject == null) "" else record.swapObject.toString()
        rentPeriodText.value = if(record.rentalPeriod == null) "" else record.rentalPeriod.toString()

        titleText.value = record.name
        descriptionText.value = record.body
        cityText.value = record.addressCity
        streetText.value = record.addressStreet
        numberText.value = record.addressNumber
    }

    fun confirm(context: Context) {
        validateFields()
        if (isError()) return

        if(isEditScreen.value) {
            updateRecord()
        } else {
            createRecord(context)
        }
    }

    private fun updateRecord(){
        if(record == null) {
            Timber.e("Record companion object must be set before sending update!")
            return
        }

        viewModelScope.launch {
            startLoading()

            val resource = repository.updateRecord(
                recordId = record!!.id,
                title = titleText.value,
                body = descriptionText.value,
                type = selectedType.value,
                category = selectedCategory.value,
                addressProvince = selectedProvince.value,
                addressCity = cityText.value,
                addressStreet = streetText.value,
                addressNumber = numberText.value,
                price = convertDouble(priceText),
                swapObject = swapObjectText.value,
                rentalPeriod = convertInteger(rentPeriodText),
            )

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    isSuccess.value = true
                    UserRecordListViewModel.signalUpdate()
                    RecordDetailsViewModel.signalUpdate()
                    SearchViewModel.signalUpdate()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    private fun createRecord(context: Context) {
        viewModelScope.launch {
            startLoading()

            val file = CommonMethods.convertBitmapToFile(imageBitmap.value!!, context)
            val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)

            val multipartBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.name, requestFile)
                .build()

            val resource = repository.createRecord(
                title = titleText.value,
                body = descriptionText.value,
                type = selectedType.value,
                category = selectedCategory.value,
                addressProvince = selectedProvince.value,
                addressCity = cityText.value,
                addressStreet = streetText.value,
                addressNumber = numberText.value,
                price = convertDouble(priceText),
                swapObject = convertText(swapObjectText),
                rentalPeriod = convertInteger(rentPeriodText),
                image = multipartBody,
            )

            when (resource) {
                is Resource.Success -> {
                    endLoading()
                    isSuccess.value = true
                    UserRecordListViewModel.signalUpdate()
                    SearchViewModel.signalUpdate()
                }
                is Resource.Error<*> -> {
                    endLoadingWithError(resource.message!!)
                }
            }
        }
    }

    // TODO: Check length validation
    private fun validateFields() {
        if (
            titleText.value.isNullOrEmpty()
            || descriptionText.value.isNullOrEmpty()
            || selectedType.value.isNullOrEmpty()
            || selectedCategory.value.isNullOrEmpty()
            || selectedProvince.value.isNullOrEmpty()
            || cityText.value.isNullOrEmpty()
            || streetText.value.isNullOrEmpty()
            || numberText.value.isNullOrEmpty()
            || (imageBitmap.value == null && !isEditScreen.value)
        ) {
            loadError.value = Strings.emptyFieldsError
        } else if (titleText.value.length > 70) {
            loadError.value = "Tytu?? jest za d??ugi!"
        } else if (descriptionText.value.length > 1000) {
            loadError.value = "Opis jest za d??ugi!"
        } else if (cityText.value.length > 30) {
            loadError.value = "Nazwa miasta jest za d??uga!"
        } else if (streetText.value.length > 30) {
            loadError.value = "Ulica jest za d??uga!"
        } else if (numberText.value.length > 5) {
            loadError.value = "Numer domu/lokalu jest za d??ugi!"
        }
    }

    private fun convertText(text: MutableState<String>): String? {
        if (text.value.isEmpty()) return null
        return text.value
    }

    private fun convertDouble(text: MutableState<String>): Double? {
        if (text.value.isEmpty()) return 1.0
        return text.value.toDouble()
    }

    private fun convertInteger(text: MutableState<String>): Int? {
        if (text.value.isEmpty()) return 1
        return text.value.toInt()
    }

    fun cleanStates() {
        val empty = ""

        selectedType.value = empty
        selectedCategory.value = empty
        selectedProvince.value = empty

        priceText.value = empty
        swapObjectText.value = empty
        rentPeriodText.value = empty

        titleText.value = empty
        descriptionText.value = empty
        cityText.value = empty
        streetText.value = empty
        numberText.value = empty
    }
}