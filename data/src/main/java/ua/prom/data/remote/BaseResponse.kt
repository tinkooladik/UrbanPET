package ua.prom.data.remote

import com.google.gson.annotations.SerializedName


data class BaseError(
    @SerializedName("message") var message: String? = null
)