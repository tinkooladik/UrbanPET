package com.tinkooladik.urbanpet.common.livedata

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


interface LiveDataObserveProtocol {

    fun <T> observe(data: LiveData<T>, observer: (T?) -> Unit) {
        if (this is LifecycleOwner) {
            data.observe(if (this is Fragment) viewLifecycleOwner else this, Observer { observer.invoke(it) })
        }
    }
}