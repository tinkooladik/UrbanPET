package com.tinkooladik.urbanpet.common.layout

import androidx.annotation.LayoutRes
import androidx.annotation.StringRes


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LayoutSettings(
    @LayoutRes val layoutId: Int = 0,
    @StringRes val title: Int = 0,
    val setWithoutBinding: Boolean = false
)