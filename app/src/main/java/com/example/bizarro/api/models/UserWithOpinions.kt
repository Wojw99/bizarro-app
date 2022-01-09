package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName

data class UserWithOpinions(
    @SerializedName("user")
    val userProfile: UserProfile,
    @SerializedName("comment")
    val opinions: List<Opinion>,
)
