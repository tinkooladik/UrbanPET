package com.tinkooladik.urbanpet.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BaseObservable

class SimpleAdapter<VM : BaseObservable>(private val holderLayout: Int, private val variableId: Int) :
    BaseAdapter<VM, SimpleAdapter<VM>.SimpleBindingHolder<VM>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleBindingHolder<VM> {
        val v = LayoutInflater.from(parent.context).inflate(holderLayout, parent, false)
        return SimpleBindingHolder(v)
    }

    inner class SimpleBindingHolder<VM : BaseObservable>(v: View) : BaseBindingViewHolder<VM>(v, variableId)
}