package com.example.bizarro.api.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class UserProfile (
    @SerializedName("id")
    val userId: Long,
    @SerializedName("created_date")
    val creationDate: LocalDate,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("url")
    val imagePath: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,
)

//{
//    "id": 1,
//    "username": "admin",
//    "description": "ble ble ble",
//    "email": "konmat@wp.pl",
//    "url": null,
//    "address_city": "Konrad",
//    "address_province": "Konrad",
//    "address_street": "Konrad",
//    "address_number": "Konrad",
//    "created_date": "2022-01-02T15:30:58.098493",
//    "phone": "791588490",
//    "firstName": "Konrad",
//    "lastName": "Konrad"
//}