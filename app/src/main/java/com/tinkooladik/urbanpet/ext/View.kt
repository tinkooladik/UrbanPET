package com.tinkooladik.urbanpet.ext

import android.app.Activity
import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


fun ViewGroup.inflate(@LayoutRes layout: Int, attach: Boolean = false): View =
    LayoutInflater.from(this.context).inflate(layout, this, attach)

fun View.showKeyboard() {
    this.post {
        requestFocus()
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Fragment.hideKeyboard() {
    val imm = context?.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Context.dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()

fun Context.pxToDp(px: Int) = (px / resources.displayMetrics.density).toInt()

fun TextView.underline() {
    val content = SpannableString(text.toString())
    content.setSpan(UnderlineSpan(), 0, content.length, 0)
    text = content
}