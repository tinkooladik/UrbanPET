package ua.prom.domain.mapper


interface MapperFrom<in FROM, out TO> {

    fun mapFrom(item: FROM): TO
}