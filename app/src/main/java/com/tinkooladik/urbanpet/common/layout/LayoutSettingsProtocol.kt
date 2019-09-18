package com.tinkooladik.urbanpet.common.layout


interface LayoutSettingsProtocol {

    fun getLayoutTitle(): Int = getAnnotation().title

    fun getLayoutId(): Int = getAnnotation().layoutId

    fun isWithoutBinding(): Boolean = getAnnotation().setWithoutBinding

    private fun getAnnotation(): LayoutSettings =
        javaClass.getAnnotation(LayoutSettings::class.java)
            ?: throw RuntimeException("Cannot find LayoutSettings annotation")
}