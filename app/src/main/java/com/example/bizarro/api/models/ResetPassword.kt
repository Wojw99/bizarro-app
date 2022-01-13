package com.example.bizarro.api.models


import com.google.gson.annotations.SerializedName

data class ResetPassword(
    @SerializedName("confirm_password")
    val confirmPassword: String,
    @SerializedName("new_password")
    val newPassword: String,
    @SerializedName("reset_password_token")
    val resetPasswordToken: String
)