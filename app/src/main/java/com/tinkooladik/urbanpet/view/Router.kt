package com.tinkooladik.urbanpet.view

import androidx.fragment.app.FragmentManager

interface Router {

    fun changeState(manager: FragmentManager?, state: RouterState?)
}

enum class RouterState {
    HOME,
    DEFINITION,
    GO_BACK
}