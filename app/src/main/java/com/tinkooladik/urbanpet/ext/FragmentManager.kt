package com.tinkooladik.urbanpet.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.FragmentTransaction
import com.tinkooladik.urbanpet.R


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun FragmentManager.replace(
    tag: String, frameId: Int, fragmentCreator: () -> Fragment
) {
    replaceFragment(this, tag, fragmentCreator, frameId)
}

fun FragmentManager.replaceBackStack(
    tag: String, frameId: Int, animate: Boolean = true, fragmentCreator: () -> Fragment
) {
    replaceFragmentBackStack(this, tag, fragmentCreator, frameId, animate)
}

fun FragmentManager.replaceBackStackNotCopy(
    tag: String, frameId: Int, animate: Boolean = true, fragmentCreator: () -> Fragment
) {
    if (findFragmentByTag(tag) != null) {
        popBackStackImmediate(tag, POP_BACK_STACK_INCLUSIVE)
    }
    replaceFragmentBackStack(this, tag, fragmentCreator, frameId, animate)
}

fun FragmentActivity.pop() {
    supportFragmentManager.popBackStack()
}

private fun replaceFragment(
    fragmentManager: FragmentManager, tag: String, fragmentCreator: () -> Fragment, frameId: Int
) {
    val fragment = fragmentManager.findFragmentByTag(tag) ?: fragmentCreator.invoke()
    fragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
    if (!fragment.isAdded)
        fragmentManager.inTransaction {
            replace(frameId, fragment, tag)
        }
}

private fun replaceFragmentBackStack(
    fragmentManager: FragmentManager?, tag: String, fragmentCreator: () -> Fragment, frameId: Int, animate: Boolean
) {
    fragmentManager?.let {

        val oldFragment = fragmentManager.findFragmentByTag(tag) ?: fragmentCreator.invoke()
        if (!oldFragment.isAdded)
            fragmentManager.inTransaction {
                if (animate) {
                    setCustomAnimations(
                        R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right
                    )
                }
                replace(frameId, oldFragment, tag)
                    .addToBackStack(tag)
            }
    }
}