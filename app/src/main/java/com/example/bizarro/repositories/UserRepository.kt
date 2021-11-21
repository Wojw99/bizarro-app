package com.example.bizarro.repositories

import com.example.bizarro.data.remote.BizarroApi
import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.data.remote.responses.UserProfile
import com.example.bizarro.util.Resource
import com.example.bizarro.util.Strings
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(
    private val api: BizarroApi
) {
    val userId: Long = 0

    suspend fun getUserProfile(userId: Long) : Resource<UserProfile> {
        delay(1000L)
        val response = try {
            api.getUserProfile(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun getUserOpinions(userId: Long) : Resource<List<Opinion>> {
        delay(1000L)
        val response = try {
            api.getUserOpinions(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun addUserOpinion(opinion: Opinion) : Resource<Opinion> {
        delay(1000L)
        val response = try {
            api.addUserOpinion(opinion)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }
}