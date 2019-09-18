package ua.prom.domain


import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.reactivestreams.Subscriber
import ua.prom.domain.mapper.MapperTo

abstract class UseCase<ResultType : Any, ParamType : Any>(private val schedulerProvider: SchedulersProvider) {

    private val subscription = CompositeDisposable()
        get() = if (field.isDisposed) CompositeDisposable() else field

    protected abstract fun buildFlowable(params: ParamType?): Flowable<ResultType>

    protected open fun validateParams(params: ParamType?): ParamType = params!!

    fun execute(
        params: ParamType?,
        onNext: (ResultType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {

        val item = buildFlowable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeBy(
                onNext = { onNext(it); onComplete() },
                onError = { onError(it); onComplete() },
                onComplete = { onComplete() })
        subscription.add(item)
    }

    fun <NewType> execute(
        params: ParamType?,
        mapper: MapperTo<ResultType, NewType>,
        onNext: (NewType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {
        val item = buildFlowable(params)
            .map { mapper.mapTo(it) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ onNext(it); onComplete() }, { onError(it); onComplete() }, { onComplete() })
        subscription.add(item)
    }

    fun execute(
        onNext: (ResultType) -> Unit,
        onError: (Throwable) -> Unit = { },
        onComplete: () -> Unit = {}
    ) {
        execute(
            null, onNext = { onNext(it); onComplete() },
            onError = { onError(it); onComplete() },
            onComplete = onComplete
        )
    }

    fun execute(params: ParamType?, subscriber: Subscriber<ResultType>) {
        buildFlowable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(subscriber)
    }

    fun unsubscribe() {
        subscription.dispose()
    }
}