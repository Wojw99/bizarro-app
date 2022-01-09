package com.example.bizarro.utils.models

data class Filter(
    var title: String? = null,
    var type: String? = null,
    var category: String? = null,
    var minPrice: Double? = null,
    var maxPrice: Double? = null,
    var province: String? = null,
    var swapObject: String? = null,
    var rentalPeriod: Int? = null,
)