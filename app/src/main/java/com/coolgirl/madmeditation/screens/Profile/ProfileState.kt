package com.coolgirl.madmeditation.screens.Profile

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

sealed class ProfileState {
    data class ImageResource(val resourceId: Int?) : ProfileState()
    data class ImageUri(val uri: String?) : ProfileState()
}

class ImageDataDeserializer : JsonDeserializer<ProfileState> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): ProfileState {
        val jsonObject = json.asJsonObject
        return if (jsonObject.has("resourceId")) {
            ProfileState.ImageResource(jsonObject.get("resourceId").asInt)
        } else {
            ProfileState.ImageUri(jsonObject.get("uri").asString)
        }
    }
}