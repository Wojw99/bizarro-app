package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.api.models.Record
import com.example.bizarro.api.models.RecordDetails
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

@ActivityScoped
class RecordRepository @Inject constructor(
    private val api: BizarroApi,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
) : NetworkingRepository() {
    // TODO: prevent before multiple calling, here or in view models

    // * * * NEW * * *
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
        rentalPeriod: Double?,
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

    // * * * OLD * * *
    suspend fun getRecordList(
        limit: Int?,
        offset: Int?,
        name: String?,
        city: String?,
        province: String?,
        type: String?,
        category: String?,
    ): Resource<List<Record>> {
        val response = try {
            api.getRecordList(limit, offset, name, city, province, type, category)
        } catch (e: Exception) {
            Timber.e(e)
            val errorText = parseError(e)
            return Resource.Error(errorText)
        }
        return Resource.Success(response)
    }
}