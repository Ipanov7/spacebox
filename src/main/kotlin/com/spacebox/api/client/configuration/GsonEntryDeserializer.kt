package com.spacebox.api.client.configuration

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.spacebox.api.domain.common.Entry
import java.lang.reflect.Type


class GsonEntryDeserializer : JsonDeserializer<Entry> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Entry {
        val jObject: JsonObject = json?.asJsonObject!!
        val hash = jObject.get("Hash").asString
        val size = jObject["Size"].asLong
        return Entry(hash = hash, size = size, type = com.spacebox.api.domain.common.Type.FILE)
    }
}