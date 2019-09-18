package com.tinkooladik.urbanpet.di

import android.content.Context
import android.preference.PreferenceManager
import com.tinkooladik.urbanpet.AppSchedulerProvider
import com.tinkooladik.urbanpet.di.scope.PerActivity
import com.tinkooladik.urbanpet.view.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ua.prom.data.local.PreferenceHelper
import ua.prom.domain.SchedulersProvider
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun providePrefs(context: Context): PreferenceHelper =
            PreferenceHelper(PreferenceManager.getDefaultSharedPreferences(context))
    }

    @Binds
    @Singleton
    abstract fun scheduler(scheduler: AppSchedulerProvider): SchedulersProvider

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeInjector(): HomeActivity
}