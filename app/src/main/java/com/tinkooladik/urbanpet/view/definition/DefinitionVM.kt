package com.tinkooladik.urbanpet.view.definition

import androidx.databinding.BaseObservable

class DefinitionVM : BaseObservable() {
    var id: Int? = null
    var term: String? = null
    var definition: String? = null
    var link: String? = null
    var thumbsUp: Int? = null
    var thumbsDown: Int? = null
    var sounds: List<String>? = null
    var author: String? = null
    var date: String? = null
    var example: String? = null
}