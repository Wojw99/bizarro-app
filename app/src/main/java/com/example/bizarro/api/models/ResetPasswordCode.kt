package com.example.bizarro.api.models


import com.google.gson.annotations.SerializedName

data class ResetPasswordCode(
    @SerializedName("Status")
    val status: String,
)