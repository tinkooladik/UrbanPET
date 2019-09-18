package ua.prom.data

import android.content.Context
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

private const val API_HOST = "x-rapidapi-host"
private const val API_KEY = "x-rapidapi-key"

object ApiServiceFactory {

    fun makeService(
        url: String,
        apiHost: String,
        apiKey: String,
        gson: Gson,
        isDebug: Boolean,
        context: Context
    ): ApiService =
        makeService(
            url,
            makeClient(
                makeLogger(isDebug),
                CacheInterceptor(context),
                makeHeaders(apiHost, apiKey)
            ),
            gson, ApiService::class.java
        )

    private fun makeClient(
        logger: HttpLoggingInterceptor,
        cacheInterceptor: CacheInterceptor,
        headersInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cacheInterceptor.cache)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(logger)
            .addInterceptor(headersInterceptor)
            .build()

    private fun <T> makeService(url: String, okHttpClient: OkHttpClient, gson: Gson, cl: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxCallAdapterWrapperFactory(RxJava2CallAdapterFactory.create()))
            .build().create(cl)

    private fun makeLogger(isDebug: Boolean) =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.tag("OkHttpClient").i(it) })
            .apply {
                level = if (isDebug) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }

    private fun makeHeaders(apiHost: String, apiKey: String) =
        Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader(API_HOST, apiHost)
                .addHeader(API_KEY, apiKey)
                .build()
            chain.proceed(request)
        }
}