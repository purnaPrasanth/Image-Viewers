package com.purna.httpclient.exception

/**
 * Mapper for Mapping Http Response Code to [HttpException]
 */

interface ICodeToExceptionMapper {
    fun mapCodeToException(code: Int): HttpException?
}