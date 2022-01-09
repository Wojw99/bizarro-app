package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.*
import com.example.bizarro.managers.TokenManager
import com.example.bizarro.ui.AppState
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class UserRepository @Inject constructor(
    private val api: BizarroApi,
    private val tokenManager: TokenManager,
) {
    var userId: Long? = null
    var accessToken: String? = null

    /**
     * Post login values and fill appState's access token with response access token.
     */
    suspend fun login(username: String, password: String): Resource<Token> {
        val response = try {
            Timber.d("Login...")
            api.login(username, password)
        } catch (e: Exception) {
            Timber.e(e)
            val errorText = parseLoginError(e)
            return Resource.Error(errorText)
        }
        // TODO: Should it be here?
        // TODO: Remove log
        tokenManager.saveAccessToken(response.accessToken)
        return Resource.Success(response)
    }

    private fun parseLoginError(e: Exception): String {
        if (e is HttpException) {
            if (e.code() == 401) {
                return Strings.errorIncorrectEmailOrPassword
            }
        }
        return Strings.unknownError;
    }

    /**
     * Get user profile data and fill appState's userId with response userId.
     * TODO: Deal with arguments (accordingly to endpoint info)
     */
    suspend fun getUserMe(): Resource<UserProfile> {
        if (tokenManager.isUserNotSignedIn()) return Resource.Error(Strings.userNotSignedInError)

        val response = try {
            api.getUserMe(tokenManager.getAuthHeader())
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(Strings.unknownError)
        }
        return Resource.Success(response)
    }
}