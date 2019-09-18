package ua.prom.data

import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
annotation class ErrorType(val type: KClass<*>)

@Retention(AnnotationRetention.RUNTIME)
annotation class Authorized()