package com.tinkooladik.urbanpet.common.adapter

import androidx.databinding.BaseObservable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class BaseAdapter<VM : BaseObservable, VH : BaseBindingViewHolder<VM>>
    : RecyclerView.Adapter<VH>() {

    var onItemClickListener: ((item: VM) -> Unit)? = null

    private var ignoreDiffUtil = false

    var updateNotifier: (() -> Unit)? = null
        set(value) {
            ignoreDiffUtil = true
            field = value
        }


    var items: List<VM> by Delegates.observable(listOf()) { _, old, new ->
        if (ignoreDiffUtil) {
            notifyDataSetChanged()
            updateNotifier?.invoke()
        } else {
            val result = DiffUtil.calculateDiff(ItemsDiffCallback(old, new))
            result.dispatchUpdatesTo(this)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setViewModel(items[position])
        holder.binding?.root?.setOnClickListener { onItemClickListener?.invoke(items[position]) }
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    inner class ItemsDiffCallback(
        private val oldItems: List<VM>,
        private val newItems: List<VM>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(oldItemPosition, newItemPosition)
    }
}