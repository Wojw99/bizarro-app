package com.example.bizarro.api.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomDateDeserializer : JsonDeserializer<LocalDate> {
    private var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    private var isoFormatter = DateTimeFormatter.ISO_DATE_TIME


    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        if (json == null) {
            throw Exception("Cannot deserialize a json object!")
        }

        return LocalDate.parse(json.asString, isoFormatter)
    }
}