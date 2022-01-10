package com.example.bizarro.repositories

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.models.*
import com.example.bizarro.managers.TokenManager
import com.example.bizarro.ui.AppState
import com.example.bizarro.utils.Resource
import com.example.bizarro.utils.Strings
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository @Inject constructor(
    private val api: BizarroApi,
    private val tokenManager: TokenManager,
) {
    var userId: Long? = null

    /**
     * Post login values and save access token.
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
        return Resource.Success(response)
    }

    private fun parseLoginError(e: Exception): String {
        if (e is HttpException) {
            if (e.code() == 401) {
                return Strings.errorIncorrectEmailOrPassword
            }
        }

        return Strings.unknownError
    }

    /**
     * Get user profile data and fill userId with response userId.
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

    suspend fun createUser(
        username: String,
        email: String,
        password: String,
        firstName: String?,
        lastName: String?,
        phone: String?,
        addressCity: String?,
        addressNumber: String?,
        addressProvince: String?,
        addressStreet: String?,
    ): Resource<UserProfile> {
        val response = try {
            api.createUser(
                AddUser(
                    username,
                    email,
                    password,
                    firstName,
                    lastName,
                    phone,
                    addressCity,
                    addressNumber,
                    addressProvince,
                    addressStreet,
                ),
            )
        } catch (e: Exception) {
            Timber.e(e)
            return Resource.Error(e.message!!)
        }
        return Resource.Success(response)
    }
}