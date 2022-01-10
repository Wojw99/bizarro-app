package com.example.bizarro.api.models


import com.google.gson.annotations.SerializedName

data class AddUser(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,

    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,

    @SerializedName("address_city")
    val addressCity: String?,
    @SerializedName("address_number")
    val addressNumber: String?,
    @SerializedName("address_province")
    val addressProvince: String?,
    @SerializedName("address_street")
    val addressStreet: String?,
)