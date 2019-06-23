package com.purna.httpclient

import com.purna.base.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.InputStreamReader
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
    }

    @Test
    fun testMalGet() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("Hello World").setResponseCode(200))

            val value = InputStreamReader(httpClient.httpGet(mockWebServer.url("").url().toString())).readText()

            assert(value == "Hello World")
        }
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }
}