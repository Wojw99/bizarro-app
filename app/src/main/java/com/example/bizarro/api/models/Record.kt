package com.example.bizarro.api.models

import java.time.LocalDate

data class Record(
    val id: Long,
    val userId: Long,
    val name: String,
    val body: String,
    val creationDate: LocalDate,
    val address: Address,
    val type: String,
    val category: Category,
    val purchasePrice: Double?,
    val rentalPeriod: Int?,
    val rentalPrice: Double?,
    val salePrice: Double?,
    val swapObject: String?,
    val imagePath: String?,
)