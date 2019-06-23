package com.purna.httpclient.exception

interface ICodeToExceptionMapper {
    fun mapCodeToException(code: Int): HttpException?
}