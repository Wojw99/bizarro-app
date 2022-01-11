package com.example.bizarro.repositories

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.api.models.Record

class CompareRepository  {
    var compareList = mutableStateOf(mutableListOf<Record>())
}