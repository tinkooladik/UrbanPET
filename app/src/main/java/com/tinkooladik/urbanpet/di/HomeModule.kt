package com.tinkooladik.urbanpet.di

import com.tinkooladik.urbanpet.di.scope.PerActivity
import com.tinkooladik.urbanpet.di.scope.PerFragment
import com.tinkooladik.urbanpet.view.Router
import com.tinkooladik.urbanpet.view.RouterImpl
import com.tinkooladik.urbanpet.view.definition.DefinitionFragment
import com.tinkooladik.urbanpet.view.home.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @Module
    companion object {

    }

    @Binds
    @PerActivity
    abstract fun provideHomeRouter(router: RouterImpl): Router

    @PerFragment
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun definitionFragment(): DefinitionFragment


}