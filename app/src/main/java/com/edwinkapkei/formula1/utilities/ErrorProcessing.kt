package com.edwinkapkei.formula1.utilities

import android.view.View
import com.google.android.material.snackbar.Snackbar

object ErrorProcessing {
    fun processHttpErrorCodes(
        code: Int,
        message: String? = null,
        view: View
    ) {
        Snackbar.make(
            view,
            getErrorMessage(code, message),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun getErrorMessage(
        code: Int,
        message: String? = null
    ): String {
        val errorMessage: String
        when (code) {
            0 -> {
                errorMessage = "No internet connection"
            }

            400 -> {
                errorMessage = "Bad request. Try again"
            }

            403 -> {
                errorMessage = "Forbidden. Try again"
            }

            404 -> {
                errorMessage = "Not found. Try again"
            }

            503 -> {
                errorMessage = "Service unavailable. Try again"
            }

            else -> {
                errorMessage =
                    message ?: "We could not complete your request. Please try again: Code $code"
            }
        }

        return errorMessage
    }
}
