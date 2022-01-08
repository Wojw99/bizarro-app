package com.example.bizarro.managers

// TODO: change name to SessionManager
class TokenManager {
    var accessToken: String? = null
        private set

    /**
     * Save access token to shared preferences
     */
    fun saveAccessToken(token: String) {
        accessToken = token
    }

    /**
     * Load access token from shared preferences
     */
    fun loadAccessToken(token: String) {

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