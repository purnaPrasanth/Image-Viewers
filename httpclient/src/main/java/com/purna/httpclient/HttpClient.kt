package com.purna.httpclient

import com.purna.base.Dispatchers
import com.purna.httpclient.connection.HttpConnection
import com.purna.httpclient.connection.IHttpConnection
import com.purna.httpclient.exception.DefaultExceptionMapper
import com.purna.httpclient.requestbuilder.ParamsBuilder
import com.purna.httpclient.requestbuilder.RequestBuilder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-20 as a part of Image-Viewers
 **/
class HttpClient constructor(
    private val dispatchers: Dispatchers,
    private val requestBuilder: RequestBuilder,
    private val connection: IHttpConnection
) {

    suspend fun httpGet(relativePath: String, getParams: () -> List<Pair<String, String>>) = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            connection.get(URL(requestBuilder.getCompleteEndPoint(relativePath, getParams())))
        }
    }

    suspend fun httpGet(getParams: () -> List<Pair<String, String>>) = httpGet("", getParams)

    suspend fun httpGet(completeUrl: String): InputStream = withContext(dispatchers.ioDispatcher) {
        connection.getStream(URL(completeUrl))
    }

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