package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Record(
    @SerializedName("id")
    val id: Long,
    @SerializedName("owner_id")
    val userId: Long,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val body: String,
    @SerializedName("created_date")
    val creationDate: LocalDate,

    @SerializedName("address_province")
    val addressProvince: String,
    @SerializedName("address_city")
    val addressCity: String,
    @SerializedName("address_street")
    val addressStreet: String,
    @SerializedName("address_number")
    val addressNumber: String,

    @SerializedName("tape_of_service")
    val type: String,
    @SerializedName("category_of_bike")
    val category: String,

    @SerializedName("price")
    val price: Double?,
    @SerializedName("rentalPeriod")
    val rentalPeriod: Int?,
    @SerializedName("swapObject")
    val swapObject: String?,

    @SerializedName("url")
    val imagePath: String?,
)