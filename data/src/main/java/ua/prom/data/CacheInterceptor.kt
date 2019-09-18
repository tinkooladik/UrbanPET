package ua.prom.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CacheInterceptor @Inject constructor(private val context: Context) : Interceptor {

    private val cacheSize = (5 * 1024 * 1024).toLong()
    val cache = Cache(context.cacheDir, cacheSize)

    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl =
            if (hasNetwork()) CacheControl.Builder().maxStale(1, TimeUnit.HOURS).build()
            else CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build()
        val request = chain.request().newBuilder().cacheControl(cacheControl).build()
        return chain.proceed(request)
    }

    private fun hasNetwork(): Boolean {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected ?: false
    }
}