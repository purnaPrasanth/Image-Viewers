package com.purna.httpclient.exception

/**
 * Default Implementation of [ICodeToExceptionMapper]
 */

class DefaultExceptionMapper : ICodeToExceptionMapper {
    override fun mapCodeToException(code: Int): HttpException? {
        return when (code) {
            in (200..299) -> null
            400 -> BadRequestException("")
            401 -> UnAuthorizedException("")
            403 -> ForbiddenException("")
            404 -> NotFoundException("")
            409 -> ConflictException("")
            500 -> InternalServerException("")
            else -> UnHandledException(code, "")
        }
    }
}