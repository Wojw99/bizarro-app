package com.example.bizarro.api.models


import com.google.gson.annotations.SerializedName

data class RecordDetails(
    @SerializedName("Post Details")
    val postDetails: Record,
    @SerializedName("Post Photo")
    val postPhoto: List<Any>
)