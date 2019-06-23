package com.purna.httpclient.requestbuilder

import java.net.URLEncoder
import java.nio.charset.Charset

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class ParamsBuilder(private val charsetEncoding: Charset = Charsets.UTF_8) {

    fun buildParams(params: List<Pair<String, String>>): String {
        if (params.isEmpty()) return ""
        val stringBuilder = StringBuilder("?")

        params.forEachIndexed { index, pair ->
            if (pair.first.isEmpty()) throw IllegalStateException("The Query Param Key can't be empty")
            stringBuilder.append("${pair.first}=${URLEncoder.encode(pair.second, charsetEncoding.toString())}")
            if (index != params.size - 1) stringBuilder.append("&")
        }

        return stringBuilder.toString()
    }
}