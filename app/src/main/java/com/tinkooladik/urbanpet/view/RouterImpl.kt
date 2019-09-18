package com.tinkooladik.urbanpet.view

import androidx.fragment.app.FragmentManager
import com.tinkooladik.urbanpet.R
import com.tinkooladik.urbanpet.ext.replace
import com.tinkooladik.urbanpet.ext.replaceBackStack
import com.tinkooladik.urbanpet.view.definition.DefinitionFragment
import com.tinkooladik.urbanpet.view.home.HomeFragment
import javax.inject.Inject

class RouterImpl @Inject constructor() : Router {

    private val layoutId = R.id.container

    override fun changeState(manager: FragmentManager?, state: RouterState?) {
        when (state) {
            RouterState.HOME -> manager?.replace(state.name, layoutId) { HomeFragment() }
            RouterState.DEFINITION -> manager?.replaceBackStack(state.name, layoutId) { DefinitionFragment() }
            else -> manager?.popBackStack()
        }
    }
}