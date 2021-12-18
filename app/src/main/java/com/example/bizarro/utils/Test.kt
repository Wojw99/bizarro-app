package com.example.bizarro.utils

import java.time.LocalDate

fun main(){
    val date = LocalDate.of(2021, 11, 16)
    val date2 = LocalDate.now()
    val diff = date2.minusDays(date.toEpochDay())
    print(diff.toEpochDay())
}