package com.purna.httpclient.exception

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

@RunWith(JUnit4::class)
class DefaultExceptionMapperTest {

    lateinit var codeToExceptionMap: MutableMap<Int, HttpException?>
    lateinit var defaultExceptionMapper: DefaultExceptionMapper

    @Before
    fun before() {
        defaultExceptionMapper = DefaultExceptionMapper()
        codeToExceptionMap = mutableMapOf(
            200 to null,
            400 to BadRequestException(""),
            401 to UnAuthorizedException(""),
            403 to ForbiddenException(""),
            404 to NotFoundException(""),
            409 to ConflictException(""),
            500 to InternalServerException(""),
            303 to UnHandledException(303, "")
        )
    }

    @Test
    fun testCodes() {
        codeToExceptionMap.keys.forEach {
            if (defaultExceptionMapper.mapCodeToException(it)?.javaClass != codeToExceptionMap[it]?.javaClass)
                assert(false)
        }
        assert(true)
    }

    @After
    fun after() {
        codeToExceptionMap.clear()
    }

}