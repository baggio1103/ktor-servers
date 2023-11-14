package javajedi.com.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserRatings : IntIdTable() {
    val value = long("value")
    val film = reference("film", StarWarsFilms)
    val user = reference("user", Users)
}

class UserRating(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserRating>(UserRatings)
    var value by UserRatings.value
    var film by StarWarsFilm referencedOn UserRatings.film
    var user by User referencedOn UserRatings.user
    override fun toString(): String {
        return "UserRating[value: $value, film: ${film.name}, user: ${user.name}]"
    }
}
