package com.example.bizarro.repositories

import android.util.Log
import com.example.bizarro.data.remote.BizarroApi
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.util.Resource
import com.example.bizarro.util.Strings
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class RecordRepository @Inject constructor(
    private val api: BizarroApi
) {
    companion object{
        private val TAG = "RecordRepository"
    }

    suspend fun getRecordList(limit: Int, offset: Int) : Resource<List<Record>>{
        val response = try {
            api.getRecordList(limit, offset)
        } catch (e: Exception) {
            Timber.d(e) // TODO: Check what is it
            return Resource.Error("An unknown error ocurred")
        }
        return Resource.Success(response)
    }

    suspend fun getRecordDetails(recordId: Long) : Resource<Record>{
        val response = try {
            api.getRecordDetails(recordId)
        } catch (e: Exception) {
            Timber.d(e) // TODO: Check what is it
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }
}