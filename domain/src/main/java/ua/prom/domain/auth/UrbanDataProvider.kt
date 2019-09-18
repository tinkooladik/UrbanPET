package ua.prom.domain.auth

import io.reactivex.Flowable
import ua.prom.domain.ApiResponse


interface UrbanDataProvider {

    fun define(term: String?): Flowable<ApiResponse<List<DefinitionDm>>>
}

data class DefinitionDm(
    val id: Int? = null,
    val term: String? = null,
    val definition: String? = null,
    val link: String? = null,
    val thumbsUp: Int? = null,
    val thumbsDown: Int? = null,
    val sounds: List<String>? = null,
    val author: String? = null,
    val date: String? = null,
    val example: String? = null
)