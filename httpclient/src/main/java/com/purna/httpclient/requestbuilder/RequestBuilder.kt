package com.purna.httpclient.requestbuilder

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Helper Class for Building request
 * @param baseUrl Base URL for the request (should end with /)
 * @param paramsBuilder [ParamsBuilder] for building param string
 */

class RequestBuilder(
    private val baseUrl: String,
    private val paramsBuilder: ParamsBuilder
) {

    init {
        if (!baseUrl.endsWith("/")) throw IllegalStateException("Base Url must end with /")
    }

    /**
     * Get complete endpoint without relative path
     */
    fun getCompleteEndPoint(params: List<Pair<String, String>>) = getCompleteEndPoint("", params)

    /**
     * Get complete endpoint wit relative path
     * @throws IllegalStateException if relative path start with /
     */
    fun getCompleteEndPoint(relativePath: String, params: List<Pair<String, String>>): String {
        if (relativePath.isNotEmpty() && relativePath.startsWith("/")) throw IllegalStateException("Relative path shouldn't start with /")
        return if (relativePath.isEmpty()) {
            baseUrl.substring(0, baseUrl.length - 1)
        } else {
            baseUrl
        } + relativePath + paramsBuilder.buildParams(params)
    }
}