package com.purna.httpclient.requestbuilder

import java.lang.IllegalStateException

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class RequestBuilder(
    private val baseUrl: String,
    private val paramsBuilder: ParamsBuilder
) {

    fun getCompleteEndPoint(params: List<Pair<String, String>>) = getCompleteEndPoint("", params)

    fun getCompleteEndPoint(relativePath: String, params: List<Pair<String, String>>): String {
        if (!baseUrl.endsWith("/")) throw IllegalStateException("Base Url must end with /")
        if (relativePath.isNotEmpty() && relativePath.startsWith("/")) throw IllegalStateException("Relative path shouldn't start with /")
        return baseUrl + relativePath + paramsBuilder.buildParams(params)
    }
}