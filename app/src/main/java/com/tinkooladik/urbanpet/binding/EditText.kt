package com.tinkooladik.urbanpet.binding

import android.widget.EditText
import androidx.databinding.BindingAdapter


class EditTextBinding {

    companion object {
        @BindingAdapter("bind:onImeAction")
        @JvmStatic
        fun EditText.onEditorAction(action: () -> Unit) {
            setOnEditorActionListener { _, _, _ ->
                action.invoke()
                true
            }
        }
    }
}