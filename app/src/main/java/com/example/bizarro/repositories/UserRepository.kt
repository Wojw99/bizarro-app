package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.api.models.Record
import com.example.bizarro.api.models.UserProfile
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(
    private val api: BizarroApi
) {
    val userId: Long = 0

    suspend fun getUserProfile(userId: Long) : Resource<UserProfile> {
        val response = try {
            api.getUserProfile(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun getUserOpinions(userId: Long) : Resource<List<Opinion>> {
        val response = try {
            api.getUserOpinions(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun getUserRecords(userId: Long) : Resource<List<Record>> {
        val response = try {
            api.getUserRecords(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun addUserOpinion(opinion: Opinion) : Resource<Opinion> {
        val response = try {
            api.addUserOpinion(opinion)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }
}