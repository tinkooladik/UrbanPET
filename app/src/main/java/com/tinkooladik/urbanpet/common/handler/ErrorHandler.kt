package com.tinkooladik.urbanpet.common.handler

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tinkooladik.urbanpet.R
import ua.prom.domain.ApiException
import ua.prom.domain.InvalidParamsException
import ua.prom.domain.UnknownException
import java.net.ConnectException
import java.net.SocketTimeoutException


interface ErrorHandler {

    fun extractMessage(resources: Resources, error: Throwable?): String? = null

    fun handleError(error: Throwable?, function: (String?) -> Unit) {
        when (this) {
            is Fragment -> handleError(this.resources, error, function)
            is AppCompatActivity -> handleError(this.resources, error, function)
        }
    }

    private fun handleError(resources: Resources, error: Throwable?, function: (String?) -> Unit) {
        val message = extractMessage(resources, error) ?: when (error) {
            is ApiException -> error.message
            is InvalidParamsException -> resources.getString(R.string.error_invalid_param)
            is UnknownException -> resources.getString(R.string.error_unknown_error)
            is ConnectException -> resources.getString(R.string.error_no_connection)
            is SocketTimeoutException -> resources.getString(R.string.error_no_connection)
            else -> error?.message
        }
        function.invoke(message)
    }

    fun handleMessage(resId: Int?, function: (String?) -> Unit) {
        resId?.let {
            when (this) {
                is Fragment -> function.invoke(this.resources.getString(resId))
                is AppCompatActivity -> function.invoke(this.resources.getString(resId))
            }
        }
    }
}