package com.tinkooladik.urbanpet.common.adapter

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingViewHolder<VM : BaseObservable>(view: View, varId: Int) :
    RecyclerView.ViewHolder(view) {

    var binding: ViewDataBinding? = null
    private val bindingVarID = varId

    init {
        bind()
    }

    fun unbind() {
        binding?.unbind()
    }

    fun setViewModel(vm: VM) {
        binding?.setVariable(bindingVarID, vm)
        addVariable(binding)
    }

    open fun addVariable(binding: ViewDataBinding?) {}

    fun bind() {
        binding = DataBindingUtil.bind(itemView)
    }
}