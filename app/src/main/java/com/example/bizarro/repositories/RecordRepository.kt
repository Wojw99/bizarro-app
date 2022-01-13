package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.api.models.Record
import com.example.bizarro.api.models.RecordDetails
import com.example.bizarro.api.models.UpdateRecord
import com.example.bizarro.managers.TokenManager
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import okhttp3.MultipartBody
import retrofit2.HttpException
import retrofit2.http.Part
import retrofit2.http.Query
import timber.log.Timber
import java.lang.Exception
import java.net.ConnectException
import javax.inject.Inject

class RecordRepository @Inject constructor(
    private val api: BizarroApi,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
) {
    // TODO: prevent before multiple calling, here or in view models

    suspend fun getUserRecords(): Resource<List<Record>> {
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.getUserRecords(tokenManager.getAuthHeader())
        } catch (e: Exception) {
            Timber.e(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }

        return Resource.Success(response)
    }

    suspend fun createRecord(
        title: String,
        body: String,
        type: String,
        category: String,
        addressProvince: String,
        addressCity: String,
        addressStreet: String,
        addressNumber: String,
        price: Double?,
        swapObject: String?,
        rentalPeriod: Int?,
        image: MultipartBody,
    ): Resource<Record> {
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.createRecord(
                tokenManager.getAuthHeader(),
                title,
                body,
                type,
                category,
                addressProvince,
                addressCity,
                addressStreet,
                addressNumber,
                price,
                swapObject,
                rentalPeriod,
                image,
            )
        } catch (e: Exception) {
            Timber.e(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }

        return Resource.Success(response)
    }

    suspend fun getRecordDetails(recordId: Long): Resource<RecordDetails> {
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.getRecordDetails(recordId)
        } catch (e: Exception) {
            Timber.d(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }

        return Resource.Success(response)
    }

    suspend fun deleteRecord(recordId: Long): Resource<String> {
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.deleteRecord(tokenManager.getAuthHeader(), recordId)
        } catch (e: Exception) {
            Timber.d(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }

        return Resource.Success(response)
    }

    suspend fun updateRecord(
        recordId: Long,
        title: String,
        body: String,
        type: String,
        category: String,
        addressProvince: String,
        addressCity: String,
        addressStreet: String,
        addressNumber: String,
        price: Double?,
        swapObject: String?,
        rentalPeriod: Int?,
    ): Resource<String> {
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.updateRecord(
                tokenManager.getAuthHeader(),
                recordId = recordId,
                updateRecord = UpdateRecord(
                    name = title,
                    body = body,
                    type = type,
                    category = category,
                    addressProvince = addressProvince,
                    addressCity = addressCity,
                    addressStreet = addressStreet,
                    addressNumber = addressNumber,
                    price = price,
                    swapObject = swapObject,
                    rentalPeriod = rentalPeriod,
                )
            )
        } catch (e: Exception) {
            Timber.e(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }

        return Resource.Success(response)
    }

    suspend fun getFilteredRecordList(
        title: String?,
        type: String?,
        category: String?,
        minPrice: Double?,
        maxPrice: Double?,
        province: String?,
        swapObject: String?,
        rentalPeriod: Int?,
    ): Resource<List<Record>> {
        val response = try {
            api.gerFilteredRecordList(
                title = title,
                type = type,
                category = category,
                minPrice = minPrice,
                maxPrice = maxPrice,
                province = province,
                swapObject = swapObject,
                rentalPeriod = rentalPeriod,
            )
        } catch (e: Exception) {
            Timber.d(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }
        return Resource.Success(response)
    }

    private fun parseError(e: Exception): String {
        if(e is ConnectException) {
            return Strings.networkError
        } else if (e is HttpException) {
            if (e.code() == 404) {
                return Strings.notFoundError
            }
            if (e.code() == 500) {
                return Strings.internalServerError
            }
            if (e.code() == 401) {
                return Strings.error401
            }
        }
        return Strings.unknownError
    }
}