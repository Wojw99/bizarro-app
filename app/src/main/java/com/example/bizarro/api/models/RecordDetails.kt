package com.example.bizarro.api.models


import com.google.gson.annotations.SerializedName

data class RecordDetails(
    @SerializedName("Post Details")
    val details: Record,
    @SerializedName("Post Photo")
    val photos: List<Any>
)