package com.purna.httpclient.requestbuilder

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

@RunWith(JUnit4::class)
class RequestBuilderTest {

    val baseUrl = "http://www.purna.com/"
    lateinit var requestBuilder: RequestBuilder
    lateinit var paramsBuilder: ParamsBuilder

    @Before
    fun before() {
        paramsBuilder = ParamsBuilder()
        requestBuilder = RequestBuilder(baseUrl, paramsBuilder)
    }

    @Test
    fun testEndWith() {
        try {
            RequestBuilder("https://www.purna.com", paramsBuilder)
            assert(false)
        } catch (exception: Exception) {
            assert(exception is IllegalStateException)
        }
    }

    @Test
    fun testWithParams() {
        val expectedUrl = "http://www.purna.com?page=10&per_page=100"

        val actualUrl = requestBuilder.getCompleteEndPoint(listOf(Pair("page", "10"), Pair("per_page", "100")))

        assert(actualUrl == expectedUrl)
    }

    @Test
    fun testWithRelativePath() {
        val expectedUrl = "http://www.purna.com/photos?page=10&per_page=100"

        val actualUrl =
            requestBuilder.getCompleteEndPoint("photos", listOf(Pair("page", "10"), Pair("per_page", "100")))

        assert(actualUrl == expectedUrl)
    }

    @Test
    fun testWithRelativePathValidation() {
        try {
            requestBuilder.getCompleteEndPoint("/photos", listOf(Pair("page", "10"), Pair("per_page", "100")))
            assert(false)
        } catch (exception: Exception) {
            assert(exception is IllegalStateException)
        }

    }

    @After
    fun after() {

    }
}