package com.tinkooladik.urbanpet.view.definition

import com.tinkooladik.urbanpet.common.BaseFragmentVM
import com.tinkooladik.urbanpet.common.livedata.SingleLiveEvent
import ua.prom.domain.auth.DefineUseCase
import javax.inject.Inject

class DefinitionViewModel @Inject constructor(
    private val defineUseCase: DefineUseCase,
    private val definitionMapperVM: DefinitionMapperVM,
    termHolder: TermHolder
) : BaseFragmentVM() {

    val items = SingleLiveEvent<List<DefinitionVM>>()
    val term = termHolder.term

    override fun onStart() {
        loading.set(true)
        defineUseCase.execute(term,
            mapper = definitionMapperVM,
            onNext = { items.value = it },
            onError = { setError(it) },
            onComplete = { loading.set(false) })
    }
}