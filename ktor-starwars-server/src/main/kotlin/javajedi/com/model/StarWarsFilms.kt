package javajedi.com.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object StarWarsFilms: IntIdTable() {
    val sequelId = integer("sequel_id").uniqueIndex()
    val name = varchar("name", 50)
    val director = varchar("director", 50)
}

class StarWarsFilm(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<StarWarsFilm>(StarWarsFilms)
    var sequelId by StarWarsFilms.sequelId
    var name by StarWarsFilms.name
    var director by StarWarsFilms.director
    val ratings by UserRating referrersOn UserRatings.film
    override fun toString() = "StarWars[name: $name, sequelId: $sequelId, director: $director]"
}