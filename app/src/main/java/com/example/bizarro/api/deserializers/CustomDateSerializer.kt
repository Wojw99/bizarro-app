package com.example.bizarro.api.deserializers

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Remove it
class CustomDateSerializer : JsonSerializer<LocalDate> {
    private var isoFormatter = DateTimeFormatter.ISO_DATE_TIME
    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        if (src == null) {
            throw Exception("Cannot serialize a null value!")
        }
        return JsonPrimitive("${src.dayOfMonth}-${src.monthValue}-${src.year}")
    }
}