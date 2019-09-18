package com.tinkooladik.urbanpet.view.definition

import ua.prom.domain.auth.DefinitionDm
import ua.prom.domain.mapper.MapperTo
import javax.inject.Inject


class DefinitionMapperVM @Inject constructor() : MapperTo<List<DefinitionDm>, List<DefinitionVM>> {

    override fun mapTo(item: List<DefinitionDm>): List<DefinitionVM> = item.map { mapItem(it) }

    private fun mapItem(item: DefinitionDm): DefinitionVM =
        DefinitionVM().apply {
            id = item.id
            term = item.term
            definition = item.definition
            link = item.link
            thumbsUp = item.thumbsUp
            thumbsDown = item.thumbsDown
            sounds = item.sounds
            author = item.author
            date = item.date
            example = item.example
        }
}