package com.example.bizarro.ui.screens.add_record

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.FileUtils
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toFile
import androidx.lifecycle.viewModelScope
import com.example.bizarro.api.models.Address
import com.example.bizarro.api.models.Category
import com.example.bizarro.api.models.Record
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.NetworkingViewModel
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.get
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import java.time.LocalDate
import javax.inject.Inject
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.*
import java.util.*


@HiltViewModel
class AddRecordViewModel @Inject constructor(
    val appState: AppState,
    private val repository: RecordRepository,
) : NetworkingViewModel() {

    companion object {
        var record: Record? = null
    }

    val typeLabels = Constants.types
    val categoryLabels = Constants.categories
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
            updateWithRecord(record!!)
        }
    }

    override fun onCleared() {
        super.onCleared()
        record = null
    }

    private fun updateWithRecord(record: Record) {
        selectedType.value = record.type
        selectedCategory.value = record.category
        selectedProvince.value = record.addressProvince

        // TODO: Change it to type specific price handling
        priceText.value = record.price.toString()
        swapObjectText.value = record.swapObject.toString()
        rentPeriodText.value = record.rentalPeriod.toString()

        titleText.value = record.name
        descriptionText.value = record.body
        cityText.value = record.addressCity
        streetText.value = record.addressStreet
        numberText.value = record.addressNumber
    }

    fun confirmAdding(context: Context) {
        if (!allFieldsCorrect()) {
            endLoadingWithError(Strings.emptyFieldsError)
            return
        }

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
                price = convertNumber(priceText),
                swapObject = convertText(swapObjectText),
                rentalPeriod = convertNumber(rentPeriodText),
                image = multipartBody,
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

    private fun allFieldsCorrect(): Boolean {
        if (titleText.value.isNullOrEmpty()
            || descriptionText.value.isNullOrEmpty()
            || selectedType.value.isNullOrEmpty()
            || selectedCategory.value.isNullOrEmpty()
            || selectedProvince.value.isNullOrEmpty()
            || cityText.value.isNullOrEmpty()
            || streetText.value.isNullOrEmpty()
            || numberText.value.isNullOrEmpty()
            || imageBitmap.value == null
        ) {
            return false
        }
        return true
    }

    private fun convertText(text: MutableState<String>): String? {
        if (text.value.isEmpty()) return null
        return text.value
    }

    private fun convertNumber(text: MutableState<String>): Double? {
        if (text.value.isEmpty()) return null
        return text.value.toDouble()
    }

    fun cleanStates() {
        val empty = ""

        selectedType.value = empty
        selectedCategory.value = empty
        selectedProvince.value = empty

        // TODO: Change it to type specific price handling
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