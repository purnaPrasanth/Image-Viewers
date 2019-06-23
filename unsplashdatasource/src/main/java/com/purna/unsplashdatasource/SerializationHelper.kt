package com.purna.unsplashdatasource

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

fun <T> fromJson(serializer: KSerializer<T>, input: String?, default: T): T {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return if (input != null && input.isNotEmpty()) json.parse(serializer, input) else default
}

fun <T> toJson(serializer: KSerializer<T>, input: T?): String? {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return input?.let { json.stringify(serializer, input) }
}

