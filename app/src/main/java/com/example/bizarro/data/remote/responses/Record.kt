package com.example.bizarro.data.remote.responses

import java.util.*

data class Record(
    val id: Long,
    val name: String,
    val body: String,
    val creationDate: String,
    val city: String,
    val province: String,
    val price: Double,
    val type: String,
)