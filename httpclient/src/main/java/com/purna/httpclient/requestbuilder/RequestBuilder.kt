package com.purna.httpclient.requestbuilder

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class RequestBuilder(
    private val baseUrl: String,
    private val paramsBuilder: ParamsBuilder
) {

    init {
        if (!baseUrl.endsWith("/")) throw IllegalStateException("Base Url must end with /")
    }

    fun getCompleteEndPoint(params: List<Pair<String, String>>) = getCompleteEndPoint("", params)

    fun getCompleteEndPoint(relativePath: String, params: List<Pair<String, String>>): String {
        if (relativePath.isNotEmpty() && relativePath.startsWith("/")) throw IllegalStateException("Relative path shouldn't start with /")
        return if (relativePath.isEmpty()) {
            baseUrl.substring(0, baseUrl.length - 1)
        } else {
            baseUrl
        } + relativePath + paramsBuilder.buildParams(params)
    }
}