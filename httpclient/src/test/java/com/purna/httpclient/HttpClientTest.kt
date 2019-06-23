package com.purna.httpclient

import com.purna.baseandroid.Dispatchers
import com.purna.httpclient.connection.HttpConnection
import com.purna.httpclient.exception.DefaultExceptionMapper
import kotlinx.coroutines.asCoroutineDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class HttpClientTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var httpClient: HttpClient

    private val dispatchers: Dispatchers by lazy {
        Dispatchers(
            ioDispatcher = kotlinx.coroutines.Dispatchers.IO,
            commonDispatcher = kotlinx.coroutines.Dispatchers.Default,
            mainDispatcher = ThreadPoolExecutor(
                1, 1,
                0L, TimeUnit.MILLISECONDS,
                LinkedBlockingQueue<Runnable>()
            ).asCoroutineDispatcher()
        )
    }

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        httpClient = HttpClient.HttpClientBuilder(
            baseUrl = mockWebServer.url("").toString(),
            dispatchers = dispatchers,
            connectionTimeOut = 10000,
            readTimeOut = 10000
        ).build()
        mockWebServer.start()
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }
}