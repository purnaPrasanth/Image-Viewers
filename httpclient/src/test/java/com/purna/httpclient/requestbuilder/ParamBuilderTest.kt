package com.purna.httpclient.requestbuilder

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.URLEncoder

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

@RunWith(JUnit4::class)
class ParamBuilderTest {

    lateinit var paramsBuilder: ParamsBuilder
    val characterEncoding = Charsets.UTF_8
    lateinit var mapOfParamOutput: MutableMap<List<Pair<String, String>>, String>

    @Before
    fun before() {
        paramsBuilder = ParamsBuilder(characterEncoding)

        mapOfParamOutput = mutableMapOf(
            emptyList<Pair<String, String>>() to "",
            listOf(Pair("page", "10")) to "?page=10",
            listOf(Pair("PAGE", "10")) to "?PAGE=10",
            listOf(Pair("page", "10"), Pair("per_page", "100")) to "?page=10&per_page=100",
            listOf(Pair("page", "hello world")) to "?page=${URLEncoder.encode(
                "hello world",
                characterEncoding.toString()
            )}",
            listOf(Pair("page", "")) to "?page="
        )
    }

    @Test
    fun testParaBuilder() {
        mapOfParamOutput.keys.forEach {
            val params = paramsBuilder.buildParams(it)
            if (params != mapOfParamOutput[it]) assert(false)
        }
    }

    @Test
    fun testEmptyKey() {
        try {
            paramsBuilder.buildParams(listOf(Pair("", "Hello")))
            assert(false)
        } catch (exception: Exception) {
            assert(exception is IllegalStateException)
        }
    }

    @After
    fun after() {
        mapOfParamOutput.clear()
    }
}