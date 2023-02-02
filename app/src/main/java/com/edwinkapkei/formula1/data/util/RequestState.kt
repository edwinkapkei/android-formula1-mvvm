package com.edwinkapkei.formula1.data.util

sealed class RequestState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : RequestState<T>(data)
    class Error<T>(data: T? = null) : RequestState<T>(data)
    class Loading<T>(message: String?, data: T? = null) : RequestState<T>(data, message)
}