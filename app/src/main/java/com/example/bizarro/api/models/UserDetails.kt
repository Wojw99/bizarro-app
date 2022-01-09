package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName

data class UserDetails (
    @SerializedName("User info")
    val userProfile: UserProfile,
    @SerializedName("Mark info")
    val markInfo: MarkInfo,
)