package com.edwinkapkei.formula1.utilities

import io.ktor.client.plugins.ResponseException

sealed class RequestState<T : Any> {
    class Success<T : Any>(val data: T) : RequestState<T>()

    // In case of no internet connection, the code will be 0
    class Error<T : Any>(val code: Int, val message: String?, val data: T? = null) : RequestState<T>()

    class Exception<T : Any>(val e: Throwable) : RequestState<T>()

    companion object {
        suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): RequestState<T> {
            return try {
                Success(apiCall())
            } catch (e: ResponseException) {
                Error(code = e.response.status.value, message = e.response.status.description)
            } catch (e: kotlin.Exception) {
                Exception<T>(e)
            }
        }
    }
}
