package com.example.bizarro.util

import androidx.compose.runtime.mutableStateOf

object Constants {
    const val BASE_URL = "http://10.0.2.2:3000/api/"
    const val RECORD_DEFAULT_IMG_URL = "http://10.0.2.2:3000/images/record_image_default.png"

    const val FILTER_CITY = "city"
    const val FILTER_PROVINCE = "province"
    const val FILTER_TYPE = "type"
    const val FILTER_CATEGORY = "category"

    const val TYPE_SELL = "sprzedam"
    const val TYPE_BUY = "kupię"
    const val TYPE_SWAP = "zamienię"
    const val TYPE_RENT = "wypożyczę"

    const val CATEGORY_BMX = "BMX"
    const val CATEGORY_GRAVEL = "Gravel"
    const val CATEGORY_MOUNTAIN = "Górski"
    const val CATEGORY_CRUISER = "Cruiser"
    const val CATEGORY_DIRT = "Dirt/Dual"

    val provinces = listOf(
        "Śląskie",
        "Mazowieckie",
        "Lubuskie",
        "Pomorskie",
        "Opolskie",
    )

    val isDark = mutableStateOf(false)

    fun checkIsDark()
    {
        isDark.value = !isDark.value
    }
}