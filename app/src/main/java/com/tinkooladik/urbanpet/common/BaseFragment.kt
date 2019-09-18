package com.tinkooladik.urbanpet.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.tinkooladik.urbanpet.common.handler.ErrorHandler
import com.tinkooladik.urbanpet.common.handler.MessageHandler
import com.tinkooladik.urbanpet.common.handler.ToastMessageHandler
import com.tinkooladik.urbanpet.common.layout.LayoutSettingsProtocol
import com.tinkooladik.urbanpet.common.livedata.LiveDataObserveProtocol
import com.tinkooladik.urbanpet.ext.hideKeyboard
import com.tinkooladik.urbanpet.view.Router
import com.tinkooladik.urbanpet.view.RouterState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : BaseFragmentVM> : Fragment(), LayoutSettingsProtocol,
    LiveDataObserveProtocol, ErrorHandler {

    @Inject lateinit var router: Router
    @Inject lateinit var viewModel: VM
    lateinit var binding: B

    fun changeState(state: RouterState?) {
        router.changeState(fragmentManager, state)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(getVar(), viewModel)
        initMessageHandlers()
        observe(viewModel.navState) { changeState(it) }
    }

    protected abstract fun getVar(): Int

    override fun onResume() {
        super.onResume()
        binding.executePendingBindings()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    open fun getMessageHandler(): MessageHandler = ToastMessageHandler(context)

    protected fun initMessageHandlers(vm: BaseFragmentVM = viewModel) {
        observe(vm.error) { handleError(it) { getMessageHandler().showError(it) } }
        observe(vm.message) { handleMessage(it) { getMessageHandler().showMessage(it) } }
        observe(vm.hideKeyboard) { hideKeyboard() }
    }
}