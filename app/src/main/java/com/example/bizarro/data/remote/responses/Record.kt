package com.example.bizarro.data.remote.responses

import java.util.*

data class Record(
    val id: Long,
    val name: String,
    val body: String,
    val creationDate: Date,
    val address: String,
    val price: Double,
)