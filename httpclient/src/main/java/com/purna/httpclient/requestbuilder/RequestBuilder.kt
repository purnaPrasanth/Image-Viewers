package com.purna.httpclient.requestbuilder

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class RequestBuilder(
    private val baseUrl: String,
    private val paramsBuilder: ParamsBuilder
) {

    fun getCompleteEndPoint(params: List<Pair<String, String>>) = getCompleteEndPoint("", params)

    fun getCompleteEndPoint(relativePath: String, params: List<Pair<String, String>>): String {
        if (baseUrl.endsWith("/")) throw IllegalStateException("base url shouldn't end with /")
        if (relativePath.isNotEmpty() && !relativePath.startsWith("/")) throw IllegalStateException("base url should start with /")
        return baseUrl + relativePath + paramsBuilder.buildParams(params)
    }
}