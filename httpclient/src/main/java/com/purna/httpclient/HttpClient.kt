package com.purna.httpclient

import com.purna.base.Dispatchers
import com.purna.httpclient.connection.HttpConnection
import com.purna.httpclient.exception.DefaultExceptionMapper
import com.purna.httpclient.requestbuilder.ParamsBuilder
import com.purna.httpclient.requestbuilder.RequestBuilder
import kotlinx.coroutines.coroutineScope
import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-20 as a part of Image-Viewers
 **/

/**
 * Http Client To be used across App
 * @param dispatchers [Dispatchers] For dispatching various tasks
 * @param requestBuilder [RequestBuilder] for building the Request
 * @param connection [HttpConnection] for making http requests
 */
class HttpClient constructor(
    private val dispatchers: Dispatchers,
    private val requestBuilder: RequestBuilder,
    private val connection: HttpConnection
) {

    /**
     * make a GET request
     * @param relativePath Relative Path from BASE_URL
     * @param getParams a Lambda with return List of Params as [Pair]
     * @return The resulting [String] from a Network Call
     */
    suspend fun httpGet(relativePath: String, getParams: () -> List<Pair<String, String>>) = coroutineScope {
        connection.get(URL(requestBuilder.getCompleteEndPoint(relativePath, getParams())))
    }

    /**
     * make a GET request
     * @param getParams a Lambda with return List of Params as [Pair]
     * @return The resulting [String] from a Network Call
     */
    suspend fun httpGet(getParams: () -> List<Pair<String, String>>) = httpGet("", getParams)

    /**
     * make a GET request
     * @param relativePath Relative Path from BASE_URL
     * @param getParams a Lambda with return List of Params as [Pair]
     * @return The resulting [InputStream] from a Network Call
     */
    suspend fun httpGet(completeUrl: String): InputStream = connection.getStream(URL(completeUrl))

    /**
     * Builder class for [HttpClient]
     */
    class HttpClientBuilder(
        val baseUrl: String,
        private val dispatchers: Dispatchers,
        private val connectionTimeOut: Int = 15000,
        private val readTimeOut: Int = 10000
    ) {
        fun build() = HttpClient(
            dispatchers = dispatchers,
            requestBuilder = RequestBuilder(baseUrl, ParamsBuilder()),
            connection = HttpConnection(
                readTimeOut = readTimeOut,
                connectTimeOut = connectionTimeOut,
                exceptionMapper = DefaultExceptionMapper()
            )
        )
    }
}