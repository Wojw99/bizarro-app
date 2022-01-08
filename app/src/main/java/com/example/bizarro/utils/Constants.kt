package com.example.bizarro.utils

import androidx.compose.runtime.mutableStateOf

object Constants {
    const val BASE_URL = "https://bike-app-1.herokuapp.com/"
    // const val BASE_URL = "http://10.0.2.2:3000/api/"
    const val RECORD_DEFAULT_IMG_URL = "https://upload.wikimedia.org/wikipedia/commons/0/04/Bike_icon.png"
    const val USER_DEFAULT_IMG_URL = "https://upload.wikimedia.org/wikipedia/commons/7/70/User_icon_BLACK-01.png"

    const val FILTER_CITY = "city"
    const val FILTER_PROVINCE = "province"
    const val FILTER_TYPE = "type"
    const val FILTER_CATEGORY = "category"

    const val TYPE_SELL = "sprzedam"
    const val TYPE_BUY = "kupię"
    const val TYPE_SWAP = "zamienię"
    const val TYPE_RENT = "wypożyczę"

    const val CATEGORY_BMX = "bmx"
    const val CATEGORY_GRAVEL = "gravel"
    const val CATEGORY_MOUNTAIN = "górski"
    const val CATEGORY_CRUISER = "cruiser"
    const val CATEGORY_DIRT = "dirt/dual"

    const val Review_1 = "1"
    const val Review_2 = "2"
    const val Review_3 = "3"
    const val Review_4 = "4"
    const val Review_5 = "5"

    val provinces = listOf(
        "śląskie",
        "mazowieckie",
        "lubuskie",
        "pomorskie",
        "opolskie",
    )

    val categories = listOf(
        CATEGORY_BMX,
        CATEGORY_GRAVEL,
        CATEGORY_MOUNTAIN,
        CATEGORY_CRUISER,
        CATEGORY_DIRT,
    )

    val types = listOf(
        TYPE_BUY,
        TYPE_SELL,
        TYPE_SWAP,
        TYPE_RENT,
    )

    val marks = listOf(
        Review_1,
        Review_2,
        Review_3,
        Review_4,
        Review_5
    )

    val isDark = mutableStateOf(false)

    fun checkIsDark()
    {
        isDark.value = !isDark.value
    }
}