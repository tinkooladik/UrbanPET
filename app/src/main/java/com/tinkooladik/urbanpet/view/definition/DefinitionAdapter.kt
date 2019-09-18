package com.tinkooladik.urbanpet.view.definition

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.tinkooladik.urbanpet.BR
import com.tinkooladik.urbanpet.R
import com.tinkooladik.urbanpet.common.adapter.BaseAdapter
import com.tinkooladik.urbanpet.common.adapter.BaseBindingViewHolder
import com.tinkooladik.urbanpet.ext.inflate


class DefinitionAdapter :
    BaseAdapter<DefinitionVM, DefinitionAdapter.DefinitionItemViewHolder>() {

    var onLinkClick: ((DefinitionVM) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionItemViewHolder =
        DefinitionItemViewHolder(parent.inflate(R.layout.item_definition))

    inner class DefinitionItemViewHolder(view: View) :
        BaseBindingViewHolder<DefinitionVM>(view, BR.definition) {

        override fun addVariable(binding: ViewDataBinding?) {
            binding?.setVariable(
                BR.definitionItemActionHolder,
                DefinitionItemActionHolder(onLinkClick)
            )
        }
    }
}

class DefinitionItemActionHolder(
    private val linkClickListener: ((DefinitionVM) -> Unit)? = null
) {

    fun onLinkClick(item: DefinitionVM) {
        linkClickListener?.invoke(item)
    }
}