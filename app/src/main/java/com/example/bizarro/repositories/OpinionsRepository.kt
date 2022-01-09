package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.*
import com.example.bizarro.managers.TokenManager
import com.example.bizarro.ui.AppState
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class OpinionsRepository @Inject constructor(
    private val api: BizarroApi,
    private val tokenManager: TokenManager,
) {
    suspend fun getOtherUserProfile(userId: Long): Resource<UserDetails> {
        val response = try {
            api.getOtherUser(userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }

    suspend fun createOpinion(userId: Long, content: String, mark: Int): Resource<Opinion>{
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            val addOpinion = AddOpinion("opinion-$userId", content)
            api.createOpinion(tokenManager.getAuthHeader(), userId, addOpinion, mark)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }

        return Resource.Success(response)
    }

    suspend fun getUserWithOpinions(userId: Long): Resource<UserWithOpinions>{
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.getUserWithOpinions(tokenManager.getAuthHeader(), userId)
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }

        return Resource.Success(response)
    }
}