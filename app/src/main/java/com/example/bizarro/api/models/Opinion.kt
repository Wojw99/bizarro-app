package com.example.bizarro.api.models

import java.time.LocalDate

data class Opinion (
    val id: Long,
    val userId: Long,
    val creationDate: LocalDate,
    val rating: Int,
    val content: String,
)