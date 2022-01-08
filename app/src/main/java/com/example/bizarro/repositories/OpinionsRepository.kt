package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.api.models.UserProfile
import com.example.bizarro.ui.AppState
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class OpinionsRepository @Inject constructor(
    private val api: BizarroApi,
) {
    suspend fun getOtherUserProfile(userId: Long): Resource<UserProfile> {
        return Resource.Error(Strings.unknownError)
        val response = try {
            api.getUserProfile()
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun getUserOpinions(userId: Long): Resource<List<Opinion>> {
        return Resource.Error(Strings.unknownError)
        val response = try {
            api.getUserOpinions(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun addUserOpinion(opinion: Opinion): Resource<Opinion> {
        return Resource.Error(Strings.unknownError)
        val response = try {
            api.addUserOpinion(opinion)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }
}