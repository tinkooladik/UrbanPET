package com.tinkooladik.urbanpet.view.definition

import android.os.Bundle
import android.view.View
import com.tinkooladik.urbanpet.BR
import com.tinkooladik.urbanpet.R
import com.tinkooladik.urbanpet.common.BaseFragment
import com.tinkooladik.urbanpet.common.layout.LayoutSettings
import com.tinkooladik.urbanpet.databinding.FragmentDefinitionBinding
import com.tinkooladik.urbanpet.ext.initWithAdapter
import kotlinx.android.synthetic.main.fragment_definition.*
import org.jetbrains.anko.toast

@LayoutSettings(R.layout.fragment_definition)
class DefinitionFragment : BaseFragment<FragmentDefinitionBinding, DefinitionViewModel>() {

    override fun getVar() = BR.definitionViewModel

    private val adapter = DefinitionAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvDefinitions.initWithAdapter(adapter)
        observe(viewModel.items) { adapter.items = it ?: listOf() }
        adapter.onItemClickListener = { context?.toast(it.term ?: "") }
    }

}
