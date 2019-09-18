package com.tinkooladik.urbanpet

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ua.prom.domain.SchedulersProvider
import javax.inject.Inject


class AppSchedulerProvider @Inject constructor() : SchedulersProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

}