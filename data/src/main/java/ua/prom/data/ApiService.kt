package ua.prom.data

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import ua.prom.data.remote.BaseError
import ua.prom.data.remote.DefineResponse


interface ApiService {

    /* auth */

    @GET("define")
    @ErrorType(BaseError::class)
    fun define(@Query("term") term: String?): Flowable<DefineResponse>

}