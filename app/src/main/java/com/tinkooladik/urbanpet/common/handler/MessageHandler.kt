package com.tinkooladik.urbanpet.common.handler


interface MessageHandler {

    fun showMessage(message: String?)

    fun showError(message: String?)
}