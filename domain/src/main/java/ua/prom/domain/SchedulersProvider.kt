package ua.prom.domain

import io.reactivex.Scheduler


interface SchedulersProvider {

    fun io(): Scheduler

    fun ui(): Scheduler
}