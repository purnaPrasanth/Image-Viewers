package com.purna.httpclient.connection

import com.purna.httpclient.exception.BadRequestException
import com.purna.httpclient.exception.DefaultExceptionMapper
import com.purna.httpclient.exception.HttpException
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class HttpConnectionTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var httpConnection: HttpConnection

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        httpConnection = HttpConnection(
            readTimeOut = 2000,
            connectTimeOut = 2000,
            exceptionMapper = DefaultExceptionMapper()
        )
        mockWebServer.start()
    }

    @Test
    fun testSuccess() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("Hello World"))

        try {
            val url = mockWebServer.url("")
            val response = httpConnection.get(url.url())
            assert(response == "Hello World")
        } catch (exception: Exception) {
            assert(false)
        }
    }

    @Test
    fun testBadRequest() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody("Hello World"))

        try {
            val url = mockWebServer.url("")
            val response = httpConnection.get(url.url())
            assert(response == "Hello World")
        } catch (exception: HttpException) {
            assert(exception is BadRequestException)
        }
    }

    @Test
    fun testSocketTimeOut() {
        mockWebServer.enqueue(
            MockResponse().throttleBody(
                1024,
                1,
                TimeUnit.MILLISECONDS
            )
        )

        try {
            val url = mockWebServer.url("")
            httpConnection.get(url.url())
            assert(false)
        } catch (exception: Exception) {
            assert(exception is SocketTimeoutException)
        }
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }
}