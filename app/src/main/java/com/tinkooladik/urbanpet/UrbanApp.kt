package com.tinkooladik.urbanpet

import android.app.Activity
import android.app.Application
import com.tinkooladik.urbanpet.di.AppComponent
import com.tinkooladik.urbanpet.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class UrbanApp : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector() = dispatchingAndroidInjector
}