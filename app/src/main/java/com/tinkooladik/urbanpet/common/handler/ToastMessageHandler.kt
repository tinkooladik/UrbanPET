package com.tinkooladik.urbanpet.common.handler

import android.content.Context
import com.tinkooladik.urbanpet.ext.weak
import org.jetbrains.anko.longToast

class ToastMessageHandler constructor(context: Context?) : MessageHandler {

    private val context by weak(context)

    override fun showMessage(message: String?) {
        message?.let { context?.longToast(message) }
    }

    override fun showError(message: String?) {
        message?.let { context?.longToast(message) }
    }
}