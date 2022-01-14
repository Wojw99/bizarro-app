package com.example.bizarro.ui

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.utils.Constants
import java.lang.Exception
import javax.inject.Singleton

@Singleton
class AppState(
    private val applicationContext: Context,
) {
    private val darkThemeSPKey = "bizarro_dark_theme"

    val bottomMenuVisible = mutableStateOf(true)
    val isDarkTheme = mutableStateOf(loadDarkTheme())

    fun toggleDarkMode() {
        isDarkTheme.value = !isDarkTheme.value
        saveDarkTheme(isDarkTheme.value)
    }

    fun showBottomMenu() {
        if(!bottomMenuVisible.value) bottomMenuVisible.value = true
    }

    fun hideBottomMenu() {
        if(bottomMenuVisible.value) bottomMenuVisible.value = false
    }

    private fun loadDarkTheme(): Boolean  {
        val sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )

        if (sharedPreferences.contains(darkThemeSPKey)) {
            return sharedPreferences.getBoolean(darkThemeSPKey, false)
        }

        return false
    }

    private fun saveDarkTheme(theme: Boolean) {
        val sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )
        with (sharedPreferences.edit()) {
            putBoolean(darkThemeSPKey, theme)
            apply()
        }
    }
}