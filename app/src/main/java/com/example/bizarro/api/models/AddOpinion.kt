package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName

data class AddOpinion(
    @SerializedName("name")
    val title: String,
    @SerializedName("description")
    val content: String,
)
