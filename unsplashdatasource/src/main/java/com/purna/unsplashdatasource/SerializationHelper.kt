package com.purna.unsplashdatasource

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * To DeSerialize a Json String into an Object
 * @param serializer [KSerializer] to be used to de-serialize the JsonString
 * @param input JsonString
 * @param default Default value to return if input is Empty or Null
 * @return the [default] value if [input] is Empty or Null else de-serialized [input]
 */
fun <T> fromJson(serializer: KSerializer<T>, input: String?, default: T): T {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return if (input != null && input.isNotEmpty()) json.parse(serializer, input) else default
}

/**
 * To Serialize an object into an Object
 * @param serializer [KSerializer] to be used to de-serialize the JsonString
 * @param input Object to be serialized
 * @return Serialized [input]
 */
fun <T> toJson(serializer: KSerializer<T>, input: T?): String? {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
    return input?.let { json.stringify(serializer, input) }
}

