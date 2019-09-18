package ua.prom.data.remote.mappers

import ua.prom.data.remote.DefineResponse
import ua.prom.data.remote.Definition
import ua.prom.domain.auth.DefinitionDm
import ua.prom.domain.mapper.MapperTo
import javax.inject.Inject

class DefineMapper @Inject constructor() : MapperTo<DefineResponse, List<DefinitionDm>> {

    override fun mapTo(item: DefineResponse): List<DefinitionDm> =
        item.results?.map { mapItem(it) } ?: listOf()

    private fun mapItem(item: Definition): DefinitionDm = DefinitionDm(
        id = item.id,
        term = item.term,
        definition = item.definition,
        link = item.link,
        thumbsUp = item.thumbsUp,
        thumbsDown = item.thumbsDown,
        sounds = item.sounds,
        author = item.author,
        date = item.date,
        example = item.example
    )
}