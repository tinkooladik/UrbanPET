package ua.prom.domain


class InvalidParamsException : Throwable()

class UnknownException : Throwable()

class ApiException(error: String) : Throwable(error)

class UnauthorizedException : Throwable()

class NetworkException(
    val error: Any?,
    val throwable: Throwable,
    val code: Int?
) : RuntimeException()