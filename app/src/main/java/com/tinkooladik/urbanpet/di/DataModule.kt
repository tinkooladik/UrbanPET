package com.tinkooladik.urbanpet.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder().setLenient().create()
    }
}