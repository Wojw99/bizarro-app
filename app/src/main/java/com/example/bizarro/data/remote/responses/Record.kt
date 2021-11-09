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
    val type: String,
    val categorie: String,
    val purchasePrice: Double?,
    val rentalPeriod: Int?,
    val rentalPrice: Double?,
    val salePrice: Double?,
    val swapObject: String?,
    val imagePaths: List<String>?,
)