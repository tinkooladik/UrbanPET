package ua.prom.domain


data class ApiResponse<T>(
    val result: Boolean,
    val data: T? = null,
    val error: Throwable? = null
)