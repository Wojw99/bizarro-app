package com.example.bizarro.data.remote.responses

import java.time.LocalDate

data class Record(
    val id: Long,
    val userId: Long,
    val name: String,
    val body: String,
    val creationDate: LocalDate,
    val city: String,
    val province: String,
    val price: Double,
    val type: String,
)