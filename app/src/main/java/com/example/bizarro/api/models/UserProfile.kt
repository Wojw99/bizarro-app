package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class UserProfile (
    @SerializedName("id")
    val userId: Long,
    @SerializedName("created_date")
    val creationDate: LocalDate,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("")
    val firstName: String?,
    @SerializedName("url")
    val imagePath: String?,
    @SerializedName("")
    val lastName: String?,
    @SerializedName("")
    val phone: String?,
    @SerializedName("general_opinion")
    val generalOpinion: GeneralOpinion = GeneralOpinion("", 1, ""),
)