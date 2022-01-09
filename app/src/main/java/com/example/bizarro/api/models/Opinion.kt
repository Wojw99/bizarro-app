package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Opinion (
    @SerializedName("id")
    val id: Long,
    @SerializedName("created_date")
    val creationDate: LocalDate,
    @SerializedName("mark")
    val rating: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("description")
    val content: String,
)
