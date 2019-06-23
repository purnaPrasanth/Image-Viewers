package com.purna.httpclient.exception

sealed class HttpException(val errorCode: Int, val requestedUrl: String) :
    Exception("Error $errorCode: While calling $requestedUrl") {
    abstract fun copy(code: Int? = null, url: String? = null): HttpException
}

data class UnHandledException(val code: Int, val url: String) : HttpException(code, url) {
    override fun copy(code: Int?, url: String?) = UnHandledException(code ?: this.code, url ?: this.url)
}

data class BadRequestException(val url: String) : HttpException(400, url) {
    override fun copy(code: Int?, url: String?) = BadRequestException(url ?: this.url)
}

data class UnAuthorizedException(val url: String) : HttpException(401, url) {
    override fun copy(code: Int?, url: String?) = UnAuthorizedException(url ?: this.url)
}

data class ForbiddenException(val url: String) : HttpException(403, url) {
    override fun copy(code: Int?, url: String?) = ForbiddenException(url ?: this.url)
}

data class NotFoundException(val url: String) : HttpException(404, url) {
    override fun copy(code: Int?, url: String?) = NotFoundException(url ?: this.url)
}

data class ConflictException(val url: String) : HttpException(409, url) {
    override fun copy(code: Int?, url: String?) = ConflictException(url ?: this.url)
}

data class InternalServerException(val url: String) : HttpException(500, url) {
    override fun copy(code: Int?, url: String?) = InternalServerException(url ?: this.url)
}