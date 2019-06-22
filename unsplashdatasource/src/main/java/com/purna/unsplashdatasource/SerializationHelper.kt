package com.purna.unsplashdatasource

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

fun <T> fromJson(serializer: KSerializer<T>, input: String): T {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return json.parse(serializer, input)
}

fun <T> fromJsonToList(serializer: KSerializer<List<T>>, input: String): List<T> {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return json.parse(serializer, input)
}

fun <T> toJson(serializer: KSerializer<T>, input: T): Any {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return json.stringify(serializer, input)
}

