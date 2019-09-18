package ua.prom.domain.mapper


interface MapperTo<in FROM, out TO> {

    fun mapTo(item: FROM): TO
}