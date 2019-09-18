package ua.prom.data.remote

import com.google.gson.annotations.SerializedName


data class DefineResponse(
    @SerializedName("list") val results: List<Definition>? = null
)

data class Definition(
    @SerializedName("definition") val definition: String? = null,
    @SerializedName("permalink") val link: String? = null,
    @SerializedName("thumbs_up") val thumbsUp: Int? = null,
    @SerializedName("thumbs_down") val thumbsDown: Int? = null,
    @SerializedName("sound_urls") val sounds: List<String>? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("word") val term: String? = null,
    @SerializedName("defid") val id: Int? = null,
    @SerializedName("current_vote") val currentVote: String? = null,
    @SerializedName("written_on") val date: String? = null,
    @SerializedName("example") val example: String? = null
)