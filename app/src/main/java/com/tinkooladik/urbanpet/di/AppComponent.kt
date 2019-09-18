package com.tinkooladik.urbanpet.di

import android.app.Application
import android.content.Context
import com.tinkooladik.urbanpet.UrbanApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ApiModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: UrbanApp)
}