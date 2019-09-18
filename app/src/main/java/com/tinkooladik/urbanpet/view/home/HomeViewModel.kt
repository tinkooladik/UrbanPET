package com.tinkooladik.urbanpet.view.home

import com.tinkooladik.urbanpet.common.BaseFragmentVM
import com.tinkooladik.urbanpet.view.RouterState
import com.tinkooladik.urbanpet.view.definition.TermHolder
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val termHolder: TermHolder
) : BaseFragmentVM() {

    var term: String? = null

    fun define() {
        termHolder.term = term
        changeState(RouterState.DEFINITION)
    }
}