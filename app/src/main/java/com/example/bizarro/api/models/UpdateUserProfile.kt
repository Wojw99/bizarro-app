package com.example.bizarro.api.models


import com.google.gson.annotations.SerializedName

data class UpdateUserProfile(
    @SerializedName("address_street")
    val address: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,
)