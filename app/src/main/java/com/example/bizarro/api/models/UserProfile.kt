package com.example.bizarro.api.models

import java.time.LocalDate

data class UserProfile (
    val id: Long,
    val userId: Long,
    val creationDate: LocalDate,
    val description: String,
    val email: String,
    val firstName: String,
    val imagePath: String,
    val lastName: String,
    val phone: String,
    val generalOpinion: GeneralOpinion,
)