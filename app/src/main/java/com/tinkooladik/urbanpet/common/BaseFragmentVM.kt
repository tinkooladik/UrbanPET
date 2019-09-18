package com.tinkooladik.urbanpet.common

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import com.tinkooladik.urbanpet.common.livedata.SingleLiveEvent
import com.tinkooladik.urbanpet.view.RouterState
import timber.log.Timber


abstract class BaseFragmentVM : BaseObservable() {

    val error = SingleLiveEvent<Throwable>()
    val message = SingleLiveEvent<Int>()
    var hideKeyboard = SingleLiveEvent<Boolean>()
    var loading: ObservableBoolean = ObservableBoolean(false)
    val navState = SingleLiveEvent<RouterState>()

    fun changeState(state: RouterState) {
        navState.value = state
    }

    protected fun setError(error: Throwable) {
        Timber.e(error)
        this.error.value = error
    }

    open fun onStart() {}

    open fun onStop() {}
}