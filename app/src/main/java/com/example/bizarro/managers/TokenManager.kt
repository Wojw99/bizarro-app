package com.example.bizarro.managers

import android.content.Context
import java.lang.Exception

// TODO: change name to SessionManager
class TokenManager(
    private val applicationContext: Context,
) {
    private val sharedPreferencesKey = "bizarro_shared_prefs_key$%!@#_key2323"
    private val accessTokenKey = "bizarro_access_token"

    var accessToken: String? = null
        private set

    /**
     * Load access token from shared preferences
     */
    fun accessTokenExists(): Boolean {
        val sharedPreferences = applicationContext.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        if(sharedPreferences.contains(accessTokenKey)) return true
        return false
    }

    fun loadAccessToken() {
        val sharedPreferences = applicationContext.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)

        if(sharedPreferences.contains(accessTokenKey)) {
            val accessToken = sharedPreferences.getString(accessTokenKey, null)
            this.accessToken = accessToken
        } else {
            throw Exception("Access token not found in shared preferences!")
        }

        if(accessToken == null) throw Exception("Loading access token didn't succeed!")
    }

    /**
     * Save access token to shared preferences
     */
    fun saveAccessToken(accessToken: String){
        this.accessToken = accessToken
        val sharedPreferences = applicationContext.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        with (sharedPreferences.edit()) {
            putString(accessTokenKey, accessToken)
            apply()
        }
    }

    fun isUserSignedIn(): Boolean {
        if(accessToken == null) return false
        return true
    }

    fun isUserNotSignedIn(): Boolean {
        return !isUserSignedIn()
    }

    fun getAuthHeader(): String{
        return "Bearer $accessToken"
    }
}