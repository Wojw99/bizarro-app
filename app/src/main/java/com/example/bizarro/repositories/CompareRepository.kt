package com.example.bizarro.repositories

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.api.models.Record
import java.time.LocalDate

class CompareRepository {
    var compareList = mutableStateOf(
        mutableListOf<Record>()
    )

    fun getCompareList() {

    }

    fun clearCompareList() {

    }

    fun addToCompareList(record: Record) {

    }
}