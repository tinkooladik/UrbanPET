package ua.prom.data

import io.reactivex.*
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ua.prom.domain.NetworkException
import java.lang.reflect.Type
import kotlin.reflect.KClass


internal class RxCallAdapterWrapperFactory constructor(private val rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) :
    CallAdapter.Factory() {

    companion object {
        fun createAsync(): RxCallAdapterWrapperFactory {
            return RxCallAdapterWrapperFactory(RxJava2CallAdapterFactory.createAsync())
        }
    }

    private fun handleError(annotations: Array<Annotation>, retrofit: Retrofit, throwable: Throwable): Throwable {

        val errorType: ErrorType? = annotations.find { it is ErrorType } as? ErrorType

        return if (errorType != null && throwable is HttpException) {
            val error = parseError(retrofit, throwable, errorType.type)
            NetworkException(error, throwable, throwable.code())
        } else throwable
    }

    private fun parseError(retrofit: Retrofit, httpException: HttpException, kClass: KClass<*>): Any? {
        if (httpException.response().isSuccessful) {
            return null
        }
        val errorBody = httpException.response().errorBody() ?: return null
        val converter: Converter<ResponseBody, Any> = retrofit.responseBodyConverter(kClass.java, arrayOf())
        return converter.convert(errorBody)
    }

    private inner class RxCallAdapterWrapper constructor(
        private val annotations: Array<Annotation>,
        private val retrofit: Retrofit,
        private val callAdapter: CallAdapter<Any, Any>
    ) : CallAdapter<Any, Any> {

        override fun adapt(call: Call<Any>): Any {
            val any = callAdapter.adapt(call)

            if (any is Observable<*>) {
                return any.onErrorResumeNext { t: Throwable -> Observable.error(handleError(annotations, retrofit, t)) }
            }

            if (any is Maybe<*>) {
                return any.onErrorResumeNext { t: Throwable -> Maybe.error(handleError(annotations, retrofit, t)) }
            }

            if (any is Single<*>) {
                return any.onErrorResumeNext { t: Throwable -> Single.error(handleError(annotations, retrofit, t)) }
            }

            if (any is Flowable<*>) {
                return any.onErrorResumeNext { t: Throwable -> Flowable.error(handleError(annotations, retrofit, t)) }
            }

            if (any is Completable) {
                return any.onErrorResumeNext { t: Throwable ->
                    Completable.error(
                        handleError(
                            annotations,
                            retrofit,
                            t
                        )
                    )
                }
            }

            return any
        }

        override fun responseType(): Type {
            return callAdapter.responseType()
        }
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        @Suppress("UNCHECKED_CAST")
        val rxJava2CallAdapter: CallAdapter<Any, Any> =
            rxJava2CallAdapterFactory.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>
        return RxCallAdapterWrapper(annotations, retrofit, rxJava2CallAdapter)
    }
}