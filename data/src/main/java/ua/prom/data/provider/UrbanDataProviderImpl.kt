package ua.prom.data.provider

import io.reactivex.Flowable
import ua.prom.data.ApiService
import ua.prom.data.remote.mappers.DefineMapper
import ua.prom.domain.ApiResponse
import ua.prom.domain.auth.DefinitionDm
import ua.prom.domain.auth.UrbanDataProvider
import javax.inject.Inject

class UrbanDataProviderImpl @Inject constructor(
    private val api: ApiService,
    private val defineMapper: DefineMapper
) : UrbanDataProvider {

    override fun define(term: String?): Flowable<ApiResponse<List<DefinitionDm>>> = api.define(term)
        .map { defineMapper.mapTo(it) }
        .map { ApiResponse(true, it) }
        .onErrorReturn { ApiResponse(false, error = it) }
}