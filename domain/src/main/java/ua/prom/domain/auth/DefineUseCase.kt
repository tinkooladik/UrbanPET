package ua.prom.domain.auth

import io.reactivex.Flowable
import ua.prom.domain.SchedulersProvider
import ua.prom.domain.UseCase
import javax.inject.Inject

class DefineUseCase @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val urbanDataProvider: UrbanDataProvider
) : UseCase<List<DefinitionDm>, String>(schedulersProvider) {

    override fun buildFlowable(params: String?): Flowable<List<DefinitionDm>> =
        urbanDataProvider.define(params)
            .doOnNext {
                if (it.error != null)
                    throw it.error
            }
            .map { it.data }
}