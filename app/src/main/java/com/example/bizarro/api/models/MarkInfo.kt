package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName

data class MarkInfo(
    @SerializedName("int_mark")
    val numberMark: Double,
    @SerializedName("str_mark")
    val textMark: String,
)
