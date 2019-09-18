package com.tinkooladik.urbanpet.di

import android.content.Context
import com.google.gson.Gson
import com.tinkooladik.urbanpet.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import ua.prom.data.ApiService
import ua.prom.data.ApiServiceFactory
import ua.prom.data.local.PreferenceHelper
import ua.prom.data.local.Prefs
import ua.prom.data.provider.UrbanDataProviderImpl
import ua.prom.domain.auth.UrbanDataProvider
import javax.inject.Singleton

@Module
abstract class ApiModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideApiService(gson: Gson, context: Context): ApiService =
            ApiServiceFactory.makeService(
                BuildConfig.API_ENDPOINT,
                BuildConfig.API_HOST,
                BuildConfig.API_KEY,
                gson,
                BuildConfig.DEBUG,
                context
            )

        @JvmStatic
        @Provides
        @Singleton
        fun providePrefs(prefHelper: PreferenceHelper): Prefs =
            Prefs(prefHelper)

    }

    @Binds
    @Singleton
    abstract fun bindUrbanDataProvider(provider: UrbanDataProviderImpl): UrbanDataProvider
}