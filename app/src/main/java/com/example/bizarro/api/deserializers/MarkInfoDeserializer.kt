package com.example.bizarro.api.deserializers

import com.example.bizarro.api.models.MarkInfo
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.Exception
import java.lang.reflect.Type

class MarkInfoDeserializer : JsonDeserializer<MarkInfo> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MarkInfo {
        val safeJson = json!!
        if (safeJson.isJsonPrimitive) {
            val textMark = json.asString
            return MarkInfo(-1.0, textMark!!)
        } else {
            val jsonObj = json.asJsonObject!!
            return MarkInfo(jsonObj.get("int_mark").asDouble, jsonObj.get("str_mark").asString)
        }
    }
}