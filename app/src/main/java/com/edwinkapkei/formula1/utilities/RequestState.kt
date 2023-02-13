package com.edwinkapkei.formula1.utilities

sealed class RequestState<T : Any> {
    class Success<T : Any>(val data: T) : RequestState<T>()

    //In case of no internet connection, the code will be 0
    class Error<T : Any>(val code: Int, val message: String?, val data: T? = null) : RequestState<T>()
    class Exception<T : Any>(val e: Throwable) : RequestState<T>()
    class Loading<T : Any>(val data: T? = null) : RequestState<T>()
}