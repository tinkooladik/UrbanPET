package com.tinkooladik.urbanpet.view.home

import android.os.Bundle
import android.view.View
import com.tinkooladik.urbanpet.BR
import com.tinkooladik.urbanpet.R
import com.tinkooladik.urbanpet.common.BaseFragment
import com.tinkooladik.urbanpet.common.layout.LayoutSettings
import com.tinkooladik.urbanpet.databinding.FragmentHomeBinding

@LayoutSettings(R.layout.fragment_home)
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

//    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun getVar() = BR.homeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
