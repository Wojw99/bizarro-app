package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.Record
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class RecordRepository @Inject constructor(
    private val api: BizarroApi
) {
    suspend fun getRecordList(
        limit: Int?,
        offset: Int?,
        name: String?,
        city: String?,
        province: String?,
        type: String?,
        category: String?,
    ) : Resource<List<Record>>{
        delay(1000L)
        val response = try {
            api.getRecordList(limit, offset, name, city, province, type, category)
        } catch (e: Exception) {
            Timber.d(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun getRecordDetails(recordId: Long) : Resource<Record>{
        val response = try {
            api.getRecordDetails(recordId)
        } catch (e: Exception) {
            Timber.d(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }
}