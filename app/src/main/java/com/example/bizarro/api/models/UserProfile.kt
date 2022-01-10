package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class UserProfile (
    @SerializedName("id")
    val userId: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("created_date")
    val creationDate: LocalDate,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("url")
    val imagePath: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,
)