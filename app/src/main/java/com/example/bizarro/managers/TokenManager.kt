package com.example.bizarro.managers

import android.content.Context
import com.example.bizarro.utils.Constants
import java.lang.Exception

// TODO: change name to SessionManager
class TokenManager(
    private val applicationContext: Context,
) {
    private val accessTokenSPKey = "bizarro_access_token"

    var accessToken: String? = null
        private set

    /**
     * Load access token from shared preferences
     */
    fun accessTokenExists(): Boolean {
        val sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )
        if (sharedPreferences.contains(accessTokenSPKey)) return true
        return false
    }

    fun loadAccessToken() {
        val sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )

        if (sharedPreferences.contains(accessTokenSPKey)) {
            val accessToken = sharedPreferences.getString(accessTokenSPKey, null)
            this.accessToken = accessToken
        } else {
            throw Exception("Access token not found in shared preferences!")
        }

        if (accessToken == null) throw Exception("Loading access token didn't succeed!")
    }

    /**
     * Save access token to shared preferences
     */
    fun saveAccessToken(accessToken: String) {
        this.accessToken = accessToken
        val sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )
        with(sharedPreferences.edit()) {
            putString(accessTokenSPKey, accessToken)
            apply()
        }
    }

    fun clearAccessToken() {
        accessToken = null
        val sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )
        with(sharedPreferences.edit()) {
            remove(accessTokenSPKey)
            apply()
        }
    }

    fun isUserSignedIn(): Boolean {
        if (accessToken == null) return false
        return true
    }

    fun isUserNotSignedIn(): Boolean {
        return !isUserSignedIn()
    }

    fun getAuthHeader(): String {
        return "Bearer $accessToken"
    }
}