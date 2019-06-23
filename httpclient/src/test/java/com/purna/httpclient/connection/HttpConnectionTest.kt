package com.purna.httpclient.connection

import com.purna.httpclient.exception.BadRequestException
import com.purna.httpclient.exception.DefaultExceptionMapper
import com.purna.httpclient.exception.HttpException
import com.purna.httpclient.util.readJsonFromResource
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.SocketTimeoutException

@RunWith(JUnit4::class)
class HttpConnectionTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var httpConnection: HttpConnection
    private lateinit var jsonData: String

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        httpConnection = HttpConnection(
            readTimeOut = 1000,
            connectTimeOut = 1000,
            exceptionMapper = DefaultExceptionMapper()
        )
        jsonData = readJsonFromResource(javaClass)
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
            MockResponse().setBody("ABC").setResponseCode(200).clearHeaders().addHeader("Content-Length: 4")
        )

        try {
            val url = mockWebServer.url("photos")
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